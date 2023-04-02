package study.querydsl.domain.member.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.member.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest() throws Exception {
        //given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        //when
        Member findMember = memberJpaRepository.findById(member.getId()).get();
        List<Member> members = memberJpaRepository.findAll();
        List<Member> byUsername = memberJpaRepository.findByUsername("member1");

        //then
        assertThat(member).isEqualTo(findMember);
        assertThat(members).containsExactly(member);
        assertThat(byUsername).containsExactly(member);
    }

    @Test
    public void basicQuerydslTest() throws Exception {
        //given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        //when
        Member findMember = memberJpaRepository.findById(member.getId()).get();
        List<Member> members = memberJpaRepository.findAll_Querydsl();
        List<Member> byUsername = memberJpaRepository.findByUsername_Querydsl("member1");

        //then
        assertThat(member).isEqualTo(findMember);
        assertThat(members).containsExactly(member);
        assertThat(byUsername).containsExactly(member);
    }
}