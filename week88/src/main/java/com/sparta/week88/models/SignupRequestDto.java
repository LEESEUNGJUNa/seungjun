package com.sparta.week88.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String nickName;
    private String password;
    private String confirmpassword;
    public SignupRequestDto (String nickName,String password,String confirmpassword)
    {
        this.nickName = nickName;
        this.confirmpassword = confirmpassword;
        this.password = password;
    }
}
