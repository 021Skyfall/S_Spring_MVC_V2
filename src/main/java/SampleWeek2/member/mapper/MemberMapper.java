package SampleWeek2.member.mapper;

import SampleWeek2.member.dto.MemberPatchDTO;
import SampleWeek2.member.dto.MemberPostDTO;
import SampleWeek2.member.dto.MemberResponseDTO;
import SampleWeek2.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDTO memberPostDTO);
    Member memberPatchDtoToMember(MemberPatchDTO memberPatchDTO);
    MemberResponseDTO memberToMemberResponseDto(Member member);
    List<MemberResponseDTO> membersToMemberResponseDto(List<Member> members);
}
