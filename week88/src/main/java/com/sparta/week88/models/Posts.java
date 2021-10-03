package com.sparta.week88.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.sparta.week88.models.Comments;
import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Posts extends Timestamped{

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String title;
    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String name;
    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String content;

    @OneToMany
    private List<Comments> commentList;

    public Posts(PostsRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.name = requestDto.getName();
    }

    public void addComment(Comments comment) {
        this.commentList.add(comment);
    }

}
