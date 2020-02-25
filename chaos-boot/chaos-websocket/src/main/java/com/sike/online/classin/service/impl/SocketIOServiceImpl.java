package com.sike.online.classin.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.sike.online.classin.dto.LoginMessage;
import com.sike.online.classin.dto.Message;
import com.sike.online.classin.dto.NoticeMessage;
import com.sike.online.classin.dto.UpdateMessage;
import com.sike.online.classin.service.SocketIOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.util.StringUtils;


/**
 * @author cjj_a
 */
@Service(value = "socketIOService")
@Slf4j
public class SocketIOServiceImpl implements SocketIOService {

    //房间
    /**
     * sid，客户端
     */
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, SocketIOClient>> allClients = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, Message>> messageDatas = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, String> globalDatas = new ConcurrentHashMap<>();
    private static Integer onlineCount = 0;
    @Autowired
    private SocketIOServer ioServer;

    /**
     * Spring IoC容器创建之后，在加载SocketIOServiceImpl Bean之后启动
     *
     * @throws Exception
     */
    @PostConstruct
    private void autoStartup() throws Exception {
        start();
    }

    /**
     * Spring IoC容器在销毁SocketIOServiceImpl Bean之前关闭,避免重启项目服务端口占用问题
     *
     * @throws Exception
     */
    @PreDestroy
    private void autoStop() throws Exception {
        stop();
    }

    @Override
    public void stop() {
        if (ioServer != null) {
            ioServer.stop();
            ioServer = null;
        }
    }

    @Override
    public void start() {
        ioServer.addConnectListener(client -> {
            login(client);
            addOnlineCount();
            doStart(client);
        });

        // 监听客户端断开连接
        ioServer.addDisconnectListener(client -> {
            subOnlineCount();
            doDisconnect(client);
        });
        ioServer.addEventListener("user", String.class, (client, data, ackSender) -> {

            doUserEvent(client, data);

        });
        ioServer.addEventListener("update", UpdateMessage.class, (client, data, ackSender) -> {

            doUpdateEvent(client, data);
        });
        ioServer.addEventListener("notice", NoticeMessage.class, (client, data, ackSender) -> {

            doNoticeEvent(client, data);
        });
        ioServer.addEventListener("global", String.class, (client, data, ackSender) -> {

            doGlobalEvent(client, data);
        });

        ioServer.start();
    }

    private void doStart(SocketIOClient client) {
        String cid = client.get("cid");
        String name = client.get("name");
        String uid = client.get("uid");
        String role = client.get("role");
        if (cid != null) {
            client.joinRoom(cid);
        }
        if (uid != null) {
            ConcurrentHashMap<String, SocketIOClient> roomClient = allClients.get(cid);
            if (roomClient == null) {
                roomClient = new ConcurrentHashMap<String, SocketIOClient>();
            }
            roomClient.put(uid, client);
            allClients.put(cid, roomClient);
        }
        ConcurrentHashMap<String, Message> roomMessage = messageDatas.get(cid);
        if (roomMessage == null) {
            roomMessage = new ConcurrentHashMap<String, Message>();
        }
        Message m = roomMessage.get(uid);
        if (m == null) {
            m = new Message();
            m.setUid(uid);
            m.setName(name);
            m.setRole(role);
            m.setStatus("1");
            m.setData("{}");
            roomMessage.put(uid, m);
        }
        messageDatas.put(cid, roomMessage);

        for (String ckey : allClients.keySet()) {
            if (cid.equals(ckey)) {
                //用户表
                for (String ukey : allClients.get(ckey).keySet()) {
                    allClients.get(ckey).get(ukey).sendEvent("user", messageDatas.get(client.get("cid")));
                }
            }
        }
        //全局数据
        String globalData = globalDatas.get(cid);
        String initGlobalData = StringUtils.isEmpty(globalData) ? "{}" : globalData;
        allClients.get(cid).get(uid).sendEvent("global", initGlobalData);
        log.info("有新窗口开始监听:" + "学生" + uid + "教室" + cid + ",当前在线人数为" + getOnlineCount() + ",当前在线房间为" + getRoomCount(cid));
    }

    private void doGlobalEvent(SocketIOClient client, String data) {
        if (data != null) {
            log.info("房间 :[{}], 学生 ：[{}], msg :[{}]", (Object) client.get("cid"), (Object) client.get("uid"), data);
        }
        String cid = client.get("cid");
        String uid = client.get("uid");
        globalDatas.put(cid, data);
        for (String ckey : allClients.keySet()) {
            if (cid.equals(ckey)) {
                //用户表
                for (String ukey : allClients.get(ckey).keySet()) {
                    if (ukey.equals(uid)) {
                        continue;
                    }
                    allClients.get(ckey).get(ukey).sendEvent("global", data);
                }
            }
        }
    }


    private void doNoticeEvent(SocketIOClient client, NoticeMessage data) {
        if (data != null) {
            log.info("房间 :[{}], 学生 ：[{}], msg :[{}]", (Object) client.get("cid"), (Object) client.get("uid"), data);
        }
        ConcurrentHashMap<String, Message> list = messageDatas.get(client.get("cid"));
        for (String i : data.getTouid()) {
            list.get(i).setData(data.getData());
            allClients.get(client.get("cid")).get(i).sendEvent("notice", messageDatas.get(client.get("cid")).get(i));
        }
    }

    private void doUpdateEvent(SocketIOClient client, UpdateMessage data) {
        if (data != null) {
            log.info("房间 :[{}], 学生 ：[{}], msg :[{}]", (Object) client.get("cid"), (Object) client.get("uid"), data);
        }
        ConcurrentHashMap<String, Message> list = messageDatas.get(client.get("cid"));
        list.get(data.getFromuid()).setData(data.getData());
        for (String i : data.getTouid()) {
            allClients.get(client.get("cid")).get(i).sendEvent("update", messageDatas.get(client.get("cid")).get(data.getFromuid()));
        }
    }

    private void doUserEvent(SocketIOClient client, String data) {
        if (data != null) {
            log.info("房间 :[{}], 学生 ：[{}], msg :[{}]", (Object) client.get("cid"), (Object) client.get("uid"), data);
        }
        allClients.get(client.get("cid")).get(client.get("uid")).sendEvent("user", messageDatas.get(client.get("cid")));
    }

    private void doDisconnect(SocketIOClient client) {
        String cid = client.get("cid");
        String uid = client.get("uid");
        if (allClients.containsKey(cid)) {
            ConcurrentHashMap<String, SocketIOClient> roomClient = allClients.get(cid);
            if (roomClient.containsKey(uid)) {
                roomClient.remove(uid);
            }
            Message m = messageDatas.get(cid).get(uid);
            if (m != null) {
                m.setStatus("0");
            }
            if (roomClient.size() == 0) {
                allClients.remove(cid);
                messageDatas.remove(cid);
            }

        }

        for (String ckey : allClients.keySet()) {
            if (cid.equals(ckey)) {
                for (String key : allClients.get(ckey).keySet()) {
                    allClients.get(ckey).get(key).sendEvent("user", messageDatas.get(client.get("cid")));
                }
            }

        }
        client.disconnect();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount() + "当前在线房间为" + getRoomCount(cid));
        log.info("-----------------------------------------");
    }


    /**
     * 此方法为获取client连接中的参数，可根据需求更改
     *
     * @param client
     * @return
     */
    private void login(SocketIOClient client) {
        LoginMessage loginMessage = new LoginMessage();
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> classid = params.get("cid");
        if (classid != null && classid.size() > 0) {
            client.set("cid", classid.get(0));
        }
        List<String> userid = params.get("uid");
        if (userid != null && userid.size() > 0) {
            client.set("uid", userid.get(0));
        }
        List<String> role = params.get("role");
        if (userid != null && userid.size() > 0) {
            client.set("role", role.get(0));
        }
        List<String> name = params.get("name");
        if (name != null && name.size() > 0) {
            client.set("name", name.get(0));
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }


    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        onlineCount--;
    }


    public synchronized int getRoomCount(String cid) {
        return messageDatas.size();
    }

}
