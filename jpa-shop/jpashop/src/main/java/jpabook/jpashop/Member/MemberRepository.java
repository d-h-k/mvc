package jpabook.jpashop.Member;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager manager;

    public Long save(Member member) {
        manager.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return manager.find(Member.class, id);
    }

}
