package com.sparta.week88.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.week88.models.SignupRequestDto;
import com.sparta.week88.models.User;
import com.sparta.week88.security.UserDetailsImpl;
import com.sparta.week88.service.KakaoUserService;
import com.sparta.week88.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {


    private final UserService userService;
    private final KakaoUserService kakaoUserService;
    @Autowired
    public UserController(UserService userService,KakaoUserService kakaoUserService)
    {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    @GetMapping("/user/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) return "login";
        else return "redirect:/?alreadylogin";
    }
    @GetMapping("/user/signup")
    public String signup(@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        if (userDetails == null) return "signup";
        else return "redirect:/?alreadylogin";
    }
    @PostMapping("/user/signup") // 회원가입.
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        //중복된 닉네임일경우 처리해줘야함
        return "redirect:/user/login";
    }
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        System.out.println("saddd");
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}
