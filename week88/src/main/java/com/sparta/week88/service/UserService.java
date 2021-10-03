package com.sparta.week88.service;

import com.sparta.week88.models.SignupRequestDto;
import com.sparta.week88.models.UserRepository;
import com.sparta.week88.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService (UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void registerUser(SignupRequestDto requestDto) {
        String nickName = requestDto.getNickName();
        String password = requestDto.getPassword();
        String confirmpassword = requestDto.getConfirmpassword();

        boolean bcheck = check(nickName, password, confirmpassword);
        if(!bcheck)
        {
            throw new IllegalArgumentException("값을 확인해주세요");
        }
        User user = userRepository.findById(nickName).orElse(null);
        if(user == null)
        {
            User newUser = new User(requestDto);
            newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            userRepository.save(newUser);
            // 회원가입 진행
        }
        else
        {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
            //중복된거 있다고 알람창띄우가
        }

    }



    private boolean check(String nickName, String password, String confirmpassword) {
        String pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{4,}$";
        boolean check = Pattern.matches(pwPattern,nickName);

//        String pwPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{3,12}$";
//        Matcher matcher = Pattern.compile(pwPattern).matcher(nickName);
        if(!check)
        {
            return false;
        }
        if(password.length() < 4 || password.contains(nickName))
        {
            return false;
            //2. 최소 4글자 이상, 닉네임과 같은 값이 포함된 경우 실패. len,contains
        }
        if(!password.equals(confirmpassword))
        {
            return false;
            //3. 컨펌이랑 그냥패스워드랑 일치하는지 확인  ==
        }
        return true;
    }
}
