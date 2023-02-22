package SampleWeek2.member.service;

import SampleWeek2.exception.BusinessLogicException;
import SampleWeek2.exception.ExceptionCode;
import SampleWeek2.member.entity.Member;
import SampleWeek2.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor // MemberRepository DI
public class MemberService {
    private MemberRepository memberRepository;

    // 회원 등록
    public Member createMember(Member member) {
        // 이미 등록된 이메일인지 검증
        verifyExistsEmail(member.getEmail());

        // 회원정보 저장
        return memberRepository.save(member);
    }

    // 회원 정보 업데이트
    public Member updateMember(Member member) {
        // 존재하는 회원인지 검증
        Member findMember = findVerifiedMember(member.getMemberId());

        // 이름 정보와 휴대폰 번호 업데이트
        Optional.ofNullable(member.getName()).ifPresent(findMember::setName);
        Optional.ofNullable(member.getPhone()).ifPresent(findMember::setPhone);

        // 회원 정보 업데이트
        return memberRepository.save(findMember);
    }

    // 회원 정보 조회
    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    // 전체 회원 정보 조회
    public List<Member> findMembers() {
        return (List<Member>) memberRepository.findAll();
    }

    // 회원 정보 삭제
    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }

    // 전체 회원 정보 삭제
    public void deleteAllMember() {
        memberRepository.deleteAll();
    }

    // 이미 존재하는 회원인지 검증
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(()->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    // 이미 존재하는 이메일인지 검증
    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
