package com.sparta.week88.mockobject;

import com.sparta.week88.models.SignupRequestDto;
import com.sparta.week88.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MockTest {
    private final MockService mockService = new MockService();
    @Test
    @Order(1)
    @DisplayName("닉네임 - 3글자 이하")
    void test1() {
        SignupRequestDto test = new SignupRequestDto("ab1","diaak12","diaak12");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mockService.saveuser(test);
        });
        assertEquals(
                "4~,a-Z,0-9",
                exception.getMessage()
        );
    }
    @Test
    @Order(2)
    @DisplayName("닉네임 - 숫자없음")
    void test2() {
        SignupRequestDto test = new SignupRequestDto("tmdwns","dho12","dho12");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mockService.saveuser(test);
        });
        assertEquals(
                "4~,a-Z,0-9",
                exception.getMessage()
        );
    }
    @Test
    @Order(3)
    @DisplayName("비밀번호 - 4글자 이하")
    void test3() {
        SignupRequestDto test = new SignupRequestDto("tmdwns123","dh1","dh1");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mockService.saveuser(test);
        });
        assertEquals(
                "contain or length",
                exception.getMessage()
        );
    }
    @Test
    @Order(4)
    @DisplayName("비밀번호 - Nickname포함")
    void test4() {
        SignupRequestDto test = new SignupRequestDto("tmd123","qtmd123q","qtmd123q");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mockService.saveuser(test);
        });
        assertEquals(
                "contain or length",
                exception.getMessage()
        );
    }
    @Test
    @Order(5)
    @DisplayName("비밀번호 - 비밀번호확인 불일치")
    void test5() {
        SignupRequestDto test = new SignupRequestDto("wns123","qtmd12","qtmd123");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mockService.saveuser(test);
        });
        assertEquals(
                "confpw",
                exception.getMessage()
        );
    }
    @Test
    @Order(6)
    @DisplayName("정상케이스")
    void test6() {
        SignupRequestDto test = new SignupRequestDto("tmdwns123","vlwk120","vlwk120");
        User user = mockService.saveuser(test);
        assertEquals(user.getPassword(),"vlwk120");
        assertEquals(user.getNickName(),"tmdwns123");
    }
    @Test
    @Order(7)
    @DisplayName("정상케이스")
    void test7() {
        SignupRequestDto test = new SignupRequestDto("jjun4534","clzls12","clzls12");
        User user = mockService.saveuser(test);
        assertEquals(user.getPassword(),"clzls12");
        assertEquals(user.getNickName(),"jjun4534");
    }

    @Test
    @Order(9)
    @DisplayName("중복아이디 존재1")
    void test9() {
        SignupRequestDto fortesttest1 = new SignupRequestDto("tmdwns123","vlwk120","vlwk120");
        SignupRequestDto fortesttest2 = new SignupRequestDto("jjun4534","clzls12","clzls12");
        mockService.saveuser(fortesttest1);
        mockService.saveuser(fortesttest2);
        SignupRequestDto test = new SignupRequestDto("tmdwns123","dkdld12","dkdld12");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mockService.saveuser(test);
        });
//        assertEquals(
//                "중복",
//                exception.getMessage()
//        );
    }
    @Test
    @Order(8)
    @DisplayName("중복 아이디 존재2")
    void test10() {
        SignupRequestDto fortesttest1 = new SignupRequestDto("tmdwns123","vlwk120","vlwk120");
        SignupRequestDto fortesttest2 = new SignupRequestDto("jjun4534","clzls12","clzls12");
        mockService.saveuser(fortesttest1);
        mockService.saveuser(fortesttest2);
        SignupRequestDto test = new SignupRequestDto("jjun4534","dkdld12","dkdld12");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mockService.saveuser(test);
        });
        assertEquals(
                "중복",
                exception.getMessage()
        );
    }


}
