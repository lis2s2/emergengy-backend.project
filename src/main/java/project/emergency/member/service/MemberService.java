package project.emergency.member.service;

import project.emergency.member.dto.MemberDTO;
import project.emergency.member.entitiy.Member;

import java.util.List;

public interface MemberService {

    List<MemberDTO> getList(); // 회원 목록조회

    boolean register(MemberDTO dto); // 회원 등록

    boolean checkIdExists(String memId);

    boolean checkEmailExists(String memEmail);

    boolean login(MemberDTO dto); // 로그인

    MemberDTO readId(String id); // 회원 아이디 단건 조회

    MemberDTO readEmail(String email); // 회원 이메일 단건 조회

    String  findid(String name, String mail); // 아이디 찾기

//    String findpwd(String id, String name, String mail); // 비밀번호 찾기

    MemberDTO readPwd(String pwd);

    boolean modifyMember(String memId, String memEmail, String memPwd);

    default MemberDTO entityToDto(Member entity) {
        MemberDTO dto = MemberDTO.builder()
                .memId(entity.getMemId())
                .memPwd(entity.getMemPwd())
                .memEmail(entity.getMemEmail())
                .memName(entity.getMemName())
//                .memNick(entity.getMemNick())
                .memGrade(entity.getMemGrade())
                .memRole(entity.getMemRole())
                .memPoint(entity.getMemPoint())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }

    default Member dtoToEntity(MemberDTO dto) {
        Member entity = Member.builder()
                .memId(dto.getMemId())
                .memPwd(dto.getMemPwd())
                .memEmail(dto.getMemEmail())
                .memName(dto.getMemName())
//                .memNick(dto.getMemNick())
                .memGrade(dto.getMemGrade())
                .memRole(dto.getMemRole())
                .memPoint(dto.getMemPoint())
                .build();

        return entity;
    }
}
