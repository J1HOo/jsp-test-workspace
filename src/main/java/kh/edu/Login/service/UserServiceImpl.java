package kh.edu.Login.service;


import kh.edu.Login.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    @Override
    public UserVO login(UserVO userVO) {
        // 더미 로그인 로직 ( 추후 실제로는 DB와 연결해서 조회)
        // 아이디 : user_lee  비밀번호 : 1234 인 유저 로그인
        if("user_lee".equals(userVO.getUserId()) && "1234".equals(userVO.getUserPw())) {
            return userVO; // 로그인 성공 ~!
        }

        return null;
    }
}
