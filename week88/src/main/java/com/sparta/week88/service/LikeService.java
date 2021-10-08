package com.sparta.week88.service;

import com.sparta.week88.models.Posts;
import com.sparta.week88.models.PostsRepository;
import com.sparta.week88.models.User;
import com.sparta.week88.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class LikeService {
    private final PostsRepository postsRepository;

    public Boolean likePost(Long id, UserDetailsImpl userDetails) {
        Posts post = postsRepository.findById(id).orElse(null);
        List<User> li = post.getUserList();
        for(int i = 0 ; i<li.size();i++)
        {
            if(li.get(i).getNickName().equals(userDetails.getUser().getNickName()))
                return false;
        }
        post.getUserList().add(userDetails.getUser());
        postsRepository.save(post);
        return true;
    }
    public Boolean cancellikePost(Long id, UserDetailsImpl userDetails) {
        Posts post = postsRepository.findById(id).orElse(null);
        List<User> li = post.getUserList();
        for(int i = 0 ; i<li.size();i++)
        {
            if(li.get(i).getNickName().equals(userDetails.getUser().getNickName())) {
                li.remove(i);
                postsRepository.save(post);
                return true;
            }
        }
        return false;
    }
}
