package kh.edu.Login.controller;
// 파일명이나 폴더명에 test를 직접적으로 작성 X
// 다른 코딩 언어에서는 test 라는 명칭이 파일이나 폴더에 작성되어 있으면
// test 코드로 코딩을 읽음
// example exam tutorial 과 같은 명칭을 사용해주는 것이 좋음

import kh.edu.Login.LoginApplication;
import kh.edu.Login.service.UserService;
import kh.edu.Login.vo.UserVO;
import org.apache.catalina.User;
import org.apache.jasper.tagplugins.jstl.core.When;
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

// 웹 기반 테스트를 진행하기 위한 상태
// 많은 파일 중에서 UserController 컨트롤러만 테스트를 진행할 예정
@WebMvcTest(controllers = UserController.class)

// 메인 실행 클래스 위치 설정
// 메인 클래스로 접근해서 테스트를 진행
@ContextConfiguration(classes = LoginApplication.class)
public class UserControllerTest {
    // Mock  = 모조품 , 가짜의
    // Mock = 프로그램을 테스트할 경우 사용하는 가상의 환경 , 데이터 설정할 때 사용하는 단어
    // Mock = 행위를 검증하기 위해 사용되는 명칭
    // 프론트엔드 = MockUp 작업이 존재함 = 백엔드가 존재하지 않을 때 가상의 백엔드 데이터를 설정해서 프론트엔드를 완성
    // 추후에 백엔드가 완성되면 MockUp을 제거하고 백엔드 코드를 연결


    @Autowired
    private MockMvc mvc;
    // Spring 회사에서 MockMvc 라는 객체를 만들어 놓은 것 => 개발자들이 테스트를 진행하며 코딩을 할 수 있도록 하기 위해서
    // DB 연결이 없을 때 임의로 service 와 같은 기능 구현을 진행하기 위해 설정해서 사용
    // MockMvc = Mock = 서버 없이도 테스트를 할 수 있다. Mvc = Model - View - Controller 과정을 통해서 하길 바란다.

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
        // 추가적인 옵션 설정할 때 사용
    }

    // 로그인 성공 테스트
    // DB에 실질적으로 접근하지 못할 때 사용하는 테스트 기법
    // Contoller에 작성한 메서드 명칭과 테스트 메서드 기능명칭이 동일하지 않아도 됨!
    @Test
    public void loginSuccessTest()  throws Exception {
        UserVO dummyUser = new UserVO();
        // DB에 연결되어 있지 않기 때문에 연결이 된다는 가정하에 아이디 비밀번호 설정
        dummyUser.setUserId("user_lee");
        dummyUser.setUserPw("1234");

        when(userService.login(any(UserVO.class))).thenReturn(dummyUser);
        // 만약에 아이디 비밀번호가 일치하다면
        mvc.perform(
                // controller post 에 작성한 api endpoint 주소로 전송
                post("/login.do")
                    // 아이디 비밀번호를 설정해서 전송
                    // .param = HTML 에서 input 태그에 값을 넣은 상태
                    .param("userId","user_lee")
                    // id 나 name 명칭이 userPw 인 input 태그에 1234 를 비밀번호 작성한 상태
                    .param("userPw","1234") )
            // HTTP 응답 코드가  .isOk() = 200 인 상태일 때
            .andExpect(status().isOk())
            // view() =  html 파일에서 파일 명칭이 "loginSuccess" 인 파일로 이동하기
            .andExpect(view().name("loginSuccess"));
    }


    @Test
    public void loginFailTest() throws Exception {
        when(userService.login(any(UserVO.class))).thenReturn(null);

        mvc.perform(post("/login.do")
                .param("userId", "wrongUser")
                .param("userPw", "wrongPw"))
            .andExpect(status().isOk())
            .andExpect(view().name("loginFail"));
    }

}
