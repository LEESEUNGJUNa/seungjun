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

    @ManyToMany
    private List<User> userList;
    //사실상 좋아요한 회원.. 이렇게하면 안될것같긴한데 시간없으니..

    public Posts(PostsRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.name = requestDto.getName();
    }

    public void addComment(Comments comment) {
        this.commentList.add(comment);
    }

}
