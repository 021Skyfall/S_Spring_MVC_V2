package SampleWeek2.member.service;

import SampleWeek2.exception.BusinessLogicException;
import SampleWeek2.exception.ExceptionCode;
import SampleWeek2.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    public Member createMember(Member member) {
//        Member createdMember = member;
//        return createdMember;
        throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
    public Member updateMember(Member member) {
        Member updatedMember = member;
        return updatedMember;
    }
    public Member findMember(long memberId) {
//        Member member = new Member(1L, "asd@gamail.com", "David", "010-1111-2222");
//        return member;
        throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
    }
    public List<Member> findMembers() {
        List<Member> members = List.of( // Stub
                new Member(1L, "asd@gamail.com", "David", "010-1111-2222"),
                new Member(2L, "qwe@naver.com", "Choi", "010-1234-1234")
        );
        return members;
    }
    public Member deleteMember() {
        return null;
    }
}
