package com.cui.tech.chaos.lite.helper;

import com.github.qcloudsms.*;
import com.github.qcloudsms.httpclient.ProxyHTTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author J.C
 * @date 2020/2/25 13:55
 */
@Component
public class TencentSmsHelper {
    @Value("${app.tencent.sms.appid}")
    int appid;
    @Value("${app.tencent.sms.appkey}")
    String appkey;
    @Value("${app.tencent.sms.smsSign}")
    String smsSign;

    //指定模板ID单发短信
    public boolean send(String phoneNumber, int templateId, String[] params) {
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    templateId, params, smsSign, "", "");
            return result.result == 0;
        } catch (HTTPException e) {
            e.printStackTrace();// HTTP响应码错误
            return false;
        } catch (JSONException e) {
            e.printStackTrace();// json解析错误
            return false;
        } catch (IOException e) {
            e.printStackTrace();// 网络IO错误
            return false;
        }
    }

    //群发,200
    public boolean sendAll(String[] phoneNumbers, int templateId, String[] params) {
        try {
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            SmsMultiSenderResult result = msender.sendWithParam("86", phoneNumbers,
                    templateId, params, smsSign, "", "");  // 签名不能为空串
            return result.result == 0;
        } catch (HTTPException e) {
            e.printStackTrace();// HTTP响应码错误
            return false;
        } catch (JSONException e) {
            e.printStackTrace();// json解析错误
            return false;
        } catch (IOException e) {
            e.printStackTrace();// 网络IO错误
            return false;
        }
    }

    public boolean sendVoiceCode(String phoneNumber, String msg) {
        try {
            SmsVoiceVerifyCodeSender vvcsender = new SmsVoiceVerifyCodeSender(appid, appkey);
            SmsVoiceVerifyCodeSenderResult result = vvcsender.send("86", phoneNumber,
                    msg, 2, "");
            return result.result == 0;
        } catch (HTTPException e) {
            e.printStackTrace();// HTTP响应码错误
            return false;
        } catch (JSONException e) {
            e.printStackTrace();// json解析错误
            return false;
        } catch (IOException e) {
            e.printStackTrace();// 网络IO错误
            return false;
        }
    }

    public boolean sendVoiceNotice(String phoneNumber, String msg) {
        try {
            SmsVoicePromptSender vpsender = new SmsVoicePromptSender(appid, appkey);
            SmsVoicePromptSenderResult result = vpsender.send("86", phoneNumber,
                    2, 2, msg, "");
            return result.result == 0;
        } catch (HTTPException e) {
            e.printStackTrace();// HTTP响应码错误
            return false;
        } catch (JSONException e) {
            e.printStackTrace();// json解析错误
            return false;
        } catch (IOException e) {
            e.printStackTrace();// 网络IO错误
            return false;
        }
    }

    public boolean pullReply() {
        try {
            // Note: 短信拉取功能需要联系腾讯云短信技术支持(QQ:3012203387)开通权限
            int maxNum = 10;  // 单次拉取最大量
            SmsStatusPuller spuller = new SmsStatusPuller(appid, appkey);

            // 拉取短信回执
            SmsStatusPullCallbackResult callbackResult = spuller.pullCallback(maxNum);
            System.out.println(callbackResult);

            // 拉取回复
            SmsStatusPullReplyResult replyResult = spuller.pullReply(maxNum);
            System.out.println(replyResult);
        } catch (HTTPException e) {
            e.printStackTrace();// HTTP响应码错误
            return false;
        } catch (JSONException e) {
            e.printStackTrace();// json解析错误
            return false;
        } catch (IOException e) {
            e.printStackTrace();// 网络IO错误
            return false;
        }
        return true;
    }

    public void pullPhoneStatus(String phoneNumber) {
        try {
            int beginTime = 1511125600;  // 开始时间(unix timestamp)
            int endTime = 1511841600;    // 结束时间(unix timestamp)
            int maxNum = 10;             // 单次拉取最大量
            SmsMobileStatusPuller mspuller = new SmsMobileStatusPuller(appid, appkey);

            // 拉取短信回执
            SmsStatusPullCallbackResult callbackResult = mspuller.pullCallback("86",
                    phoneNumber, beginTime, endTime, maxNum);
            System.out.println(callbackResult);

            // 拉取回复
            SmsStatusPullReplyResult replyResult = mspuller.pullReply("86",
                    phoneNumber, beginTime, endTime, maxNum);
            System.out.println(replyResult);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

    public void uploadVoiceFile() {
        try {
            // Note: 语音文件大小上传限制400K字节
            String filePath = "path/to/example.mp3";
            byte[] content = Files.readAllBytes(Paths.get(filePath));
            VoiceFileUploader uploader = new VoiceFileUploader(appid, appkey);
            VoiceFileUploaderResult result = uploader.upload(content, VoiceFileUploader.ContentType.MP3);
            // 上传成功后，result里会带有语音文件的fid
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

    public void checkVoiceFile() {
        try {
            // Note: 这里fid来自`上传语音文件`接口返回的响应，要按语音
            //       文件fid发送语音通知，需要先上传语音文件获取fid
            String fid = "c799d10a43ec109f02f2288ca3c85b79e7700c98.mp3";
            VoiceFileStatus vfstatus = new VoiceFileStatus(appid, appkey);
            VoiceFileStatusResult result = vfstatus.get(fid);
            // result里会带有语音文件审核状态status, {0: 待审核, 1: 通过, 2: 拒绝, 3: 语音文件不存在}
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

    public void sendVoiceNoticeByFid(String phoneNumber) {
        try {
            // Note: 这里fid来自`上传语音文件`接口返回的响应，要按语音
            //       文件fid发送语音通知，需要先上传语音文件获取fid
            String fid = "c799d10a43ec109f02f2288ca3c85b79e7700c98.mp3";
            FileVoiceSender fvsender = new FileVoiceSender(appid, appkey);
            FileVoiceSenderResult result = fvsender.send("86", phoneNumber, fid, 2, "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

    public void sendVoiceByTemplate(String phoneNumber) {
        try {
            int templateId = 45221;
            String[] params = {"5678"};
            TtsVoiceSender tvsender = new TtsVoiceSender(appid, appkey);
            TtsVoiceSenderResult result = tvsender.send("86", phoneNumber,
                    templateId, params, 2, "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

    public void sendVoiceByProxy(Integer templateId, String phoneNumber) {
        try {
            // 创建一个代理httpclient
            ProxyHTTPClient httpclient = new ProxyHTTPClient("127.0.0.1", 8080, "http");

            String[] params = {"5678"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey, httpclient);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }
}
