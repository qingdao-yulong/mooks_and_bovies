package com.qdu.controller;

import com.qdu.entity.User;
import com.qdu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    private UserService userService;
    private HomepageController homepageController;

    @Autowired
    public AuthenticationController(UserService userService, HomepageController homepageController) {
        this.userService = userService;
        this.homepageController = homepageController;
    }

    @GetMapping("/login")
    public String toLogin() {
        return "authentication/login";
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        User user = userService.getUserByUsernameAndPassword(req.getParameter("username"), req.getParameter("password"));
        if (null != user) {
            httpSession.setAttribute("user", user);
            if (1 == user.getAdmin()) {
                httpSession.setAttribute("admin", 1);
            }
            return homepageController.index(model);
        } else {
            return "authentication/login_error";
        }
    }

    @GetMapping("/register")
    public String toRegister(Model model) {
        return "authentication/register";
    }

    @PostMapping("/register")
    public String register(Model model, HttpSession session, @ModelAttribute User user) {
        User user1 = userService.createUser(user);
        if (null != user1) {
            User newUser = userService.getUserByUsername(user.getUsername());
            session.setAttribute("user", newUser);
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("admin");
        return homepageController.index(model);
    }

}
