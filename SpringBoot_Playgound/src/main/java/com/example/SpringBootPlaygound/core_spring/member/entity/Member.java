package com.example.SpringBootPlaygound.core_spring.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Builder
@AllArgsConstructor
public class Member {
    private Long id;
    private String name;
    private Grade grade;
}
