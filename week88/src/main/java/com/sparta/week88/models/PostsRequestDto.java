package com.sparta.week88.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
public class PostsRequestDto {
    private String title;
    private String content;
    private String name;
}
