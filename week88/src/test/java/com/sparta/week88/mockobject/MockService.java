package com.sparta.week88.mockobject;

import com.sparta.week88.models.SignupRequestDto;
import com.sparta.week88.models.User;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.regex.Pattern;

public class MockService {
    private final MockRepository mockRepository;

    public MockService()
    {
        mockRepository = new MockRepository();
    }
    //여기는 정규식 테스트 위주로하면될것같다.
    public User saveuser(SignupRequestDto requestDto)
    {
        String nickName = requestDto.getNickName();
        String password = requestDto.getPassword();
        String cpassword = requestDto.getConfirmpassword();
        boolean bcheck = check(nickName,password,cpassword);
        return mockRepository.save(new User(requestDto));
    }

    private boolean check(String nickName, String password, String confirmpassword) {
        String pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{4,}$";
        boolean check = Pattern.matches(pwPattern,nickName);

//        String pwPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{3,12}$";
//        Matcher matcher = Pattern.compile(pwPattern).matcher(nickName);
        if(!check)
        {
            throw new IllegalArgumentException("4~,a-Z,0-9"); // 걸리면 중복임
        }
        if(password.length() < 4 || password.contains(nickName))
        {
            throw new IllegalArgumentException("contain or length"); // 걸리면 중복임
            //2. 최소 4글자 이상, 닉네임과 같은 값이 포함된 경우 실패. len,contains
        }
        if(!password.equals(confirmpassword))
        {
            throw new IllegalArgumentException("confpw"); // 걸리면 중복임
            //3. 컨펌이랑 그냥패스워드랑 일치하는지 확인  ==
        }
        return true;
    }

//    @PostMapping("/user/signup") // 회원가입.
//    public String registerUser(SignupRequestDto requestDto) {
//        userService.registerUser(requestDto);
//        //중복된 닉네임일경우 처리해줘야함
//        return "redirect:/user/login";
//    }
}
