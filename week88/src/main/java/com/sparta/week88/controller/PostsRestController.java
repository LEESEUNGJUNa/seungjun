package com.sparta.week88.controller;

import com.sparta.week88.models.Posts;
import com.sparta.week88.models.PostsRepository;
import com.sparta.week88.models.PostsRequestDto;
import com.sparta.week88.models.mydto;
import com.sparta.week88.security.UserDetailsImpl;
import com.sparta.week88.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLOutput;
import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController
@Controller
public class PostsRestController {

    private final PostsRepository postsRepository;
    private final PostsService postsService;
    @PostMapping("/api/post")
    public void createProduct(
            @RequestBody PostsRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postsService.savePost(requestDto,userDetails);

    }
    @GetMapping("/api/post")
    public List<Posts> getPosts()
    {
        //postsRepository.findAllByOrderBycreatedAtDesc();
        return postsRepository.findAllByOrderByCreatedAtDesc();
    }
    @GetMapping("/api/postone/{id}")
    public mydto getPost(@PathVariable Long id,
                         @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        Posts Post = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        mydto ret = new mydto();
        ret.setPost(Post);
        if(userDetails == null)
        {
            ret.setUserNick(null);
        }
        else
        {
            ret.setUserNick(userDetails.getUser().getNickName());
        }

        return ret;
    }

    @GetMapping("/new")
    public String newPerson(Model model)
    {
        System.out.println("나왔엉");
        Posts post = new Posts();
        model.addAttribute("person","new");
        return "new";
    }
}
