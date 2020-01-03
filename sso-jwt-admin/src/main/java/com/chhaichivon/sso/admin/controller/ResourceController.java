package com.chhaichivon.sso.admin.controller;

import com.chhaichivon.sso.admin.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Chhai Chivon on 3/1/20
 * Senior Application Developer
 */

@Controller
public class ResourceController {
    private static final String jwtTokenCookieName = "ADMIN-JWT-TOKEN";

    /*@RequestMapping("/")
    public String home() {
        return "redirect:/admin";
    }

    @RequestMapping("/admin")
    public String protectedResource() {
        return "admin";
    }*/

    @RequestMapping("/logout")
    public String logout(HttpServletResponse httpServletResponse) {
        CookieUtil.clear(httpServletResponse, jwtTokenCookieName);
        return "redirect:/";
    }
}
