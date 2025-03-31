package kh.edu.Login.controller;

import jakarta.servlet.http.HttpSession;
import kh.edu.Login.service.UserService;
import kh.edu.Login.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService service;

    @PostMapping("/login.do")
    public String memberLogin(UserVO user, HttpSession session) {
        UserVO loginUser = service.login(user);

        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);
            logger.info("로그인 성공: userId={}, 세션 생성됨", loginUser.getUserId());
            return "loginSuccess";
        } else {
            logger.warn("로그인 실패: 입력값 userId={}, userPw={}", user.getUserId(), user.getUserPw());
            return "loginFail";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}