package com.sparta.week88.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    //카카오 유저는 email이 id..
    //중복시 로그인 거절됨, 이미아이디있다고하면서.
    @Id
    private String nickName;

    @Column(nullable = false)
    private String password;

    public User(String nickname, String password)
    {
        this.nickName = nickname;
        this.password = password;

    }
    public User(SignupRequestDto requestDto)
    {
        this.nickName = requestDto.getNickName();
        this.password = requestDto.getPassword();

    }
}
