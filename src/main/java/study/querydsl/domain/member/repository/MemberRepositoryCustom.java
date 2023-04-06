package study.querydsl.domain.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.querydsl.domain.member.dto.MemberSearchCond;
import study.querydsl.domain.member.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCond cond);

    Page<MemberTeamDto> searchPageSimple(MemberSearchCond cond, Pageable pageable);

    Page<MemberTeamDto> searchPageComplex(MemberSearchCond cond, Pageable pageable);
}
