package com.chhaichivon.sso.auth.controller;

import com.chhaichivon.sso.auth.util.CookieUtil;
import com.chhaichivon.sso.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chhai Chivon on 3/1/20
 * Senior Application Developer
 */

@Controller
public class LoginController {

    private String jwtTokenCookieName = "";
    private static final String appJwtTokenCookieName = "APP-JWT-TOKEN";
    private static final String adminJwtTokenCookieName = "ADMIN-JWT-TOKEN";

    private static final String signingKey = "signingKey";
    private static final Map<String, String> credentials = new HashMap<>();

    @Value("${app.server.url}")
    private String appServerUrl;
    @Value("${admin.server.url}")
    private String adminServerUrl;

    public LoginController() {
        credentials.put("user1", "user1");
        credentials.put("user2", "user2");
    }

    @RequestMapping("/")
    public String home(){
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model){

        System.out.println("redirect => "+redirect);
        System.out.println("username => "+username);
        System.out.println("password => "+password);

        if (username == null || !credentials.containsKey(username) || !credentials.get(username).equals(password)){
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }

        if(redirect.equals(appServerUrl)){
            jwtTokenCookieName = appJwtTokenCookieName;
        }else{
            jwtTokenCookieName = adminJwtTokenCookieName;
        }

        System.out.println("jwtTokenCookieName => "+jwtTokenCookieName);
        String token = JwtUtil.generateToken(signingKey, username);
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

        return "redirect:" + redirect;
    }
}
