package com.sparta.week88.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Comments extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String userNick;

//    @Column(nullable = false)
//    private Long postId;

    @Column(nullable = false)
    private String content;
    public Comments(String usernick, String content)
    {
        this.userNick = usernick;
//        this.postId = postid;
        this.content = content;
    }

    public Comments(CommentsRequestDto dto)
    {
//        this.postId = dto.getPostId();
        this.content = dto.getContent();
    }
}
