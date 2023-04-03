package study.querydsl.domain.member.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.member.dto.MemberSearchCond;
import study.querydsl.domain.member.dto.MemberTeamDto;
import study.querydsl.domain.member.entity.Member;
import study.querydsl.domain.team.entity.Team;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;
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


    @Test
    public void searchTest() throws Exception {
        //given

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        //when
        MemberSearchCond cond = new MemberSearchCond();
        cond.setAgeGoe(35);
        cond.setAgeLoe(40);
        cond.setTeamName("teamB");

        List<MemberTeamDto> result = memberJpaRepository.search(cond);
        //then
        assertThat(result).extracting("username")
                .containsExactly("member4");
    }
}