package com.sparta.week88.controller;

import com.sparta.week88.models.Comments;
import com.sparta.week88.models.CommentsDto;
import com.sparta.week88.models.CommentsRequestDto;
import com.sparta.week88.models.PostsRequestDto;
import com.sparta.week88.security.UserDetailsImpl;
import com.sparta.week88.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Controller
public class CommentsController {
    private final CommentsService commentsService;

    @PostMapping("/api/comment")
    public void addcomment(
            @RequestBody CommentsRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        commentsService.savecomment(requestDto,userDetails);
    }

    @GetMapping("/checklogin")
    public Boolean checkLogin(@AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails == null) {
            return true;
        }
        else return false;
    }

    @DeleteMapping("/api/deletecomment/{id}")
    public void deletecomment(@PathVariable Long id){
        commentsService.deletecomment(id);
    }

    @PutMapping("/api/editcomment")
    public void updateProduct(@RequestBody CommentsDto requestDto) {
        commentsService.UpdateComment(requestDto);
    }
}
