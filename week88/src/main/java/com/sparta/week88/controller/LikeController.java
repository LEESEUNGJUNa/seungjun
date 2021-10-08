package com.sparta.week88.controller;

import com.sparta.week88.models.SignupRequestDto;
import com.sparta.week88.security.UserDetailsImpl;
import com.sparta.week88.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Controller
public class LikeController {
    private final LikeService likeService;
    @PostMapping("/user/like/{id}") // 회원가입.
    public Boolean likePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.likePost(id,userDetails);
    }

    @PostMapping("/user/unlike/{id}") // 회원가입.
    public Boolean unlikePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.cancellikePost(id,userDetails);
    }
}
