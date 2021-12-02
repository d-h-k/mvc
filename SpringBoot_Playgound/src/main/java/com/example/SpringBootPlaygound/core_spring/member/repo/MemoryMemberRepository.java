package com.example.SpringBootPlaygound.core_spring.member.repo;

import com.example.SpringBootPlaygound.core_spring.member.entity.Member;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component //86페이지 컴포넌트 스캔
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> memory = new HashMap<>();
    // todo : ConcurrentHashMap 나중에 공부, 해쉬맵은 동시성 이슈 있음

    @Override
    public Member save(Member member) {
        return memory.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return memory.get(memberId);
    }

}
