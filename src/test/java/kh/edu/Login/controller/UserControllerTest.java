package kh.edu.Login.controller;

import kh.edu.Login.LoginApplication;
import kh.edu.Login.service.UserService;
import kh.edu.Login.vo.UserVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes = LoginApplication.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
        // 테스트 전에 실행할 코드
    }

    @Test
    public void loginSuccess() throws Exception {
        UserVO dummyUser = new UserVO();
        dummyUser.setUserId("user_lee");
        dummyUser.setUserPw("1234");

        when(userService.login(any(UserVO.class))).thenReturn(dummyUser);
        // 만약에 아이디 비밀번호가 일치하다면

        mvc.perform(post("/login.do")
                .param("userId", "user_lee")
                .param("userPw", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginSuccess"));

    }
}
