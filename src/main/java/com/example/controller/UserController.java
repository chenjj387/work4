package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // 返回 login.jsp
    }

    @PostMapping("/login")
    public ModelAndView handleLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userDAO.loginUser(username, password);
        ModelAndView mav = new ModelAndView();
        if (user != null) {
            session.setAttribute("currentUser", user);
            mav.setViewName("redirect:/welcome.jsp");
        } else {
            mav.addObject("error", "用户名或密码错误！");
            mav.setViewName("login");
        }
        return mav;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // 返回 register.jsp
    }

    @PostMapping("/register")
    public ModelAndView handleRegister(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        ModelAndView mav = new ModelAndView();
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            mav.addObject("error", "所有字段都不能为空！");
            mav.setViewName("register");
            return mav;
        }
        User newUser = new User(username, password, email);
        if (userDAO.registerUser(newUser)) {
            mav.setViewName("redirect:/login.jsp");
        } else {
            mav.addObject("error", "注册失败，用户名或邮箱可能已存在。");
            mav.setViewName("register");
        }
        return mav;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 使会话失效
        return "redirect:/index.jsp"; // 重定向到首页
    }
}