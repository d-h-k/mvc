package com.example.SpringBootPlaygound.core_spring.member.service;

import com.example.SpringBootPlaygound.core_spring.member.entity.Member;

public interface MemberService {
    Member join(Member member);

    Member findMember(long id);
}
