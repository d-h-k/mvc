package jpabook.jpashop.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional //꼭 트랜잭션이 있어야함 : JPA 에서 EntityManager는 트랜잭션 단위로 >> 영속성을 유지하기 위해..? 공부필요 @Todo
    @Rollback(true)
    public void testMember() {
        Member member = new Member();
        member.setUserName("groot");
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        assertThat(findMember.getId()).isEqualTo(member.getId()); // 아이디가 같니?
        assertThat(findMember.getUserName()).isEqualTo(member.getUserName()); // 이름이 같니?

        assertThat(findMember).isEqualTo(member);// JPA 엔티디 동일성 보장 (영속성 컨텍스트)
        // 같은 트랜잭션 내에서 저장하고(member) 다시 읽어들인(findMember) 데이터는 완전히 동일하다!
        // 1차캐쉬에 저장되어 있던거 그대로 리턴되고, 쿼리 날라가는거 한번 살펴보면 SELECT 쿼리가 아예 안날라가기 때문


    }

}