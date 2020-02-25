package com.cui.tech.chaos.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * cookie 工具类  功能: 减少重复代码，获取cookie、删除cookie
 *
 * @author Jian.Cui
 */
public class CookieUtil {
    /**
     * 返回一个可用的 cookie
     *
     * @param request
     * @param response
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if (ck.getName().equals("JSESSIONID2")) {
                    cookie = ck;
                }
            }
        }
        if (cookie == null) {
            HttpSession session = request.getSession();
            if (session == null) {
                session = request.getSession(true);
            }
            String id = session.getId();
            cookie = new Cookie("JSESSIONID2", id);
        }
        cookie.setPath("/");
        cookie.setMaxAge(3600 * 1);
        //cookie.setDomain("niao.com");
        cookie.setHttpOnly(true);
        return cookie;
    }

    /**
     * 删除cookie
     *
     * @param request
     * @param response
     * @return
     */
    public static boolean delCookie(HttpServletRequest request, HttpServletResponse response) {
        boolean DELCOOKIEOK = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID2")) { // 删除cookie
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    cookie.setDomain("niao.com");
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    // 删除redis中 键值对
                    System.out.println("注销成功");
                    DELCOOKIEOK = true;
                }
            }
        } else {
            DELCOOKIEOK = true;
        }
        return DELCOOKIEOK;
    }

    public static boolean checkCookie(HttpServletRequest request) {
        boolean CHECKCOOKIE = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID2")) { // 删除cookie
                    CHECKCOOKIE = true;
                }
            }
        }
        return CHECKCOOKIE;
    }
}
