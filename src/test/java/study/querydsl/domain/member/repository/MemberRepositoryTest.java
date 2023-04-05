package study.querydsl.domain.member.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.member.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void basicTest() throws Exception {
        //given
        Member member = new Member("member1", 10);
        memberRepository.save(member);

        //when
        Member findMember = memberRepository.findById(member.getId()).get();
        List<Member> members = memberRepository.findAll();
        List<Member> byUsername = memberRepository.findByUsername("member1");

        //then
        assertThat(member).isEqualTo(findMember);
        assertThat(members).containsExactly(member);
        assertThat(byUsername).containsExactly(member);
    }
}