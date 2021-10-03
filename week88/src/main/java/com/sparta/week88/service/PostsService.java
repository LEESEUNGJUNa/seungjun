package com.sparta.week88.service;

import com.sparta.week88.models.Posts;
import com.sparta.week88.models.PostsRepository;
import com.sparta.week88.models.PostsRequestDto;
import com.sparta.week88.models.User;
import com.sparta.week88.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public void savePost(PostsRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        requestDto.setName(user.getNickName());
        Posts post = new Posts(requestDto);
        postsRepository.save(post);
    }
}
