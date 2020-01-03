package com.chhaichivon.sso.app.controller;

import com.chhaichivon.sso.app.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Chhai Chivon on 3/1/20
 * Senior Application Developer
 */

@Controller
public class ResourceController {

    private static final String jwtTokenCookieName = "APP-JWT-TOKEN";

    @RequestMapping("/")
    public String home() {
        return "redirect:/app";
    }

    @RequestMapping("/app")
    public String protectedResource() {
        return "app";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse httpServletResponse) {
        CookieUtil.clear(httpServletResponse, jwtTokenCookieName);
        return "redirect:/";
    }
}
