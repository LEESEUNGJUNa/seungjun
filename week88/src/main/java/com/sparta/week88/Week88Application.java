package com.sparta.week88;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 시간 자동 변경이 가능하도록 합니다.
@SpringBootApplication
public class Week88Application {

    public static void main(String[] args) {
        SpringApplication.run(Week88Application.class, args);
    }

}
