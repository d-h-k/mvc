package hello.servlet.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("데이터 저장 테스트")
    void save() {
        Member member = new Member("dongdongHello", 22);
        Member saved = memberRepository.save(member);
        Member find = memberRepository.findById(saved.getId());

        assertThat(find).isEqualTo(saved);
        assertThat(saved).isEqualTo(member);
    }


    @Test
    @DisplayName("모든 데이터를 조회해서 검사하는 테스트")
    void findAll() {
        final int checkCounter = 10;
        final String prefix = "테스트맴버";
        final int magicAge = 33;
        for(int i=0 ; i<checkCounter ; i++) {
            Member member = new Member(prefix + i, magicAge + i);
            memberRepository.save(member);
        }

        List<Member> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(checkCounter);
        result.forEach(member -> {
            assertThat(member.getUsername().contains(prefix)).isTrue();
            assertThat(member.getAge()).isGreaterThanOrEqualTo(magicAge);
        });
    }
}