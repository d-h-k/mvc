package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 디비 연결 - 이시점에서 디비연결
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        // jdbc url이 꼭 >> jdbc:h2:tcp://localhost/~/test

        // code
        //t1_goodToGo(em);
        t2_find(em); // 2번만 여러번 실행해도 에러가 안남
        //t3_AutoUpdate(em);
        //t4_intro_JPQL(em);
        //t5_seq(em);

        //디비 종료
        em.close();
        emf.close();
    }


    private static void t1_goodToGo(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // em은 트랜잭션마다 일괄적인 작업마다
            Member member = new Member(2L, "Hong");
            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    private static void t2_find(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // em은 트랜잭션마다 일괄적인 작업마다
            Member member = em.find(Member.class, 1L);
            System.out.println(" 출력 : " + member.toString());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    private static void t3_AutoUpdate(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // em은 트랜잭션마다 일괄적인 작업마다
            Member member = em.find(Member.class, 1L);
            member.setName("JPA_good!!");
            System.out.println(" 출력 : " + member.toString());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    private static void t4_intro_JPQL(EntityManager em) {
        // 결국 기승전 쿼리
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            List<Member> members = em.createQuery("select m from Member as m", Member.class).getResultList();

            for (Member m : members) {
                System.out.println(m);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    private static void t5_seq(EntityManager em) {
        // 결국 기승전 쿼리
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member(201L,"hong");

            System.out.println("== before");
            em.persist(member);

            System.out.println("== after");
            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        }

    }
}
/*
주의
- 엔티티 매니져 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
- 엔티티 매니져는 쓰레드간 공유하면 절대 안됨!!(사용하고 바로바로 버려야 한다)
- JP A의 모든 데이터 변경은 트랜잭션 안에서 실행

- 우리가 트랜잭션을 안걸어도 디비는 내부적으로 다 처리한다
  - 내가 모르고 보이지 않는다고 해서 하위 컴포넌트를 꼭 모를필요는 없다..??




JPQL
- jpa쓰면 엔티티객체를 중심으로 개발(더이상 sql 아님!)
- 커밋함수를 호출하는 시점에 쿼리가 날라간다


준영속상태
-
 */
