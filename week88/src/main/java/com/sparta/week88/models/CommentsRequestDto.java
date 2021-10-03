package com.sparta.week88.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
@Getter
@Setter
public class CommentsRequestDto {
    private Long postId;
    private String content;
}

