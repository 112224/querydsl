package study.querydsl.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.querydsl.domain.member.dto.MemberSearchCond;
import study.querydsl.domain.member.dto.MemberTeamDto;
import study.querydsl.domain.member.repository.MemberJpaRepository;
import study.querydsl.domain.member.repository.MemberRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCond cond) {
        return memberJpaRepository.search(cond);
    }

    @GetMapping("/v2/members")
    public Page<MemberTeamDto> searchMemberV2(MemberSearchCond cond, Pageable pageable) {
        return memberRepository.searchPageSimple(cond, pageable);
    }

    @GetMapping("/v3/members")
    public Page<MemberTeamDto> searchMemberV3(MemberSearchCond cond, Pageable pageable) {
        return memberRepository.searchPageComplex(cond, pageable);
    }
}
