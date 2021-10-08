package com.sparta.week88.service;

import com.sparta.week88.models.*;
import com.sparta.week88.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final PostsRepository postsRepository;

    public void savecomment(CommentsRequestDto requestDto, UserDetailsImpl userDetails)
    {
        Comments comment = new Comments(requestDto);
        comment.setUserNick(userDetails.getUser().getNickName());

        Posts post= postsRepository.findById(requestDto.getPostId())
                .orElse(null);

        if (post!=null)
        {
            commentsRepository.save(comment);
            post.addComment(comment);
            postsRepository.save(post);
        }



    }
    @Transactional
    public void deletecomment(Long id) {
        Comments com = commentsRepository.findById(id)
                .orElse(null);
        Posts post = postsRepository.findById(com.getPostId())
                .orElse(null);
        List<Comments> comList = post.getCommentList();
        comList.remove(com);
        if (com != null)
        {
            commentsRepository.delete(com);
        }
        Comments newcom = commentsRepository.findById(id)
                .orElse(null);
    }

    public void UpdateComment(CommentsDto requestDto) {
        Comments com = commentsRepository.findById(requestDto.getId())
                .orElse(null);
        com.setContent(requestDto.getContent());
        commentsRepository.save(com);
    }
}
