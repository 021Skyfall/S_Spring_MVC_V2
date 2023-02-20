package SampleWeek2.member.controller;

import SampleWeek2.member.dto.MemberPatchDTO;
import SampleWeek2.member.dto.MemberPostDTO;
import SampleWeek2.member.dto.MemberResponseDTO;
import SampleWeek2.member.entity.Member;
import SampleWeek2.member.mapper.MemberMapper;
import SampleWeek2.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/members")
@Validated
@AllArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService service;
    private final MemberMapper mapper;

    @PostMapping
    public ResponseEntity postMember(@Validated @RequestBody MemberPostDTO memberPostDTO) {
        Member response = service.createMember(mapper.memberPostDtoToMember(memberPostDTO));
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Validated @RequestBody MemberPatchDTO memberPatchDTO) {
        memberPatchDTO.setMemberId(memberId);
        Member response = service.updateMember(mapper.memberPatchDtoToMember(memberPatchDTO));
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response),HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        Member response = service.findMember(memberId);
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        List<Member> members = service.findMembers();
        List<MemberResponseDTO> response = mapper.membersToMemberResponseDto(members);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {
        service.deleteMember();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
