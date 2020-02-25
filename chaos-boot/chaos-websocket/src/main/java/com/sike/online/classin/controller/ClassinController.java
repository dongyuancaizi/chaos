package com.sike.online.classin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/okya/classin")
@Api(tags = "ClassinController", description = "Classin模块")
public class ClassinController {

    //页面请求
    @GetMapping("/index/{classid}/{userid}/{role}")
    @ApiOperation(value = "Classin模块-连接", notes = "", httpMethod = "POST")
    public ModelAndView socket(@PathVariable String classid, @PathVariable String userid, @PathVariable String role) {
        ModelAndView mav = new ModelAndView("/socket1");
        mav.addObject("userid", userid);
        mav.addObject("classid", classid);
        mav.addObject("role", role);
        return mav;
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{classid}/{message}")
    @ApiOperation(value = "Classin模块-推数据", notes = "", httpMethod = "POST")
    public Map pushToWeb(@PathVariable String classid, @PathVariable String message) {
        Map result = new HashMap();
//        try {
//           // WebSocketServer.sendInfo(message, classid);
//            result.put("code", 200);
//            result.put("msg", "success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return result;

    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{classid}/{userid}/{message}")
    @ApiOperation(value = "Classin模块-推数据", notes = "", httpMethod = "POST")
    public Map pushToWeb(@PathVariable String classid, @PathVariable String userid, @PathVariable String message) {
        Map result = new HashMap();
//        try {
//            //WebSocketServer.sendInfo(message, classid, userid);
//            result.put("code", 200);
//            result.put("msg", "success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return result;

    }

    @RequestMapping("/lastmsg/{classid}/{userid}")
    @ResponseBody
    @ApiOperation(value = "Classin模块-获取最后数据", notes = "", httpMethod = "POST")
    public String getLastMsg(@PathVariable String classid, @PathVariable String userid) {
        return null;// WebSocketServer.getLastMessage(classid, userid);
    }
}