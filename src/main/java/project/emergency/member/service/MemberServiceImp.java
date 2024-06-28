package project.emergency.member.service;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.emergency.member.dto.MemberDTO;
import project.emergency.member.entitiy.Member;
import project.emergency.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<MemberDTO> getList() {

        List<Member> entityList = repository.findAll();

        List<MemberDTO> dtoList = entityList.stream()
                                            .map(entity -> entityToDto(entity))
                                            .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public boolean register(MemberDTO dto) {
//        String id = dto.getMemId();
//        MemberDTO getDtoId = readId(id);
//
//        String email = dto.getMemEmail();
//        MemberDTO getDtoEmail = readEmail(email);
//
//        if (getDtoId != null || getDtoEmail != null) {
//            return false;
//        }
        // 아이디나 이메일 중복 시 false 반환
        if (repository.existsById(dto.getMemId()) || repository.existsByMemEmail(dto.getMemEmail())) {
            alert(message);
            return false;
        }

        Member entity = dtoToEntity(dto);

        // 기본 등급 설정
        entity.setMemGrade("Regular");

        // 기본 포인트 설정
        entity.setMemPoint(0);

        // 기본 권한 설정
        entity.setMemRole("ROLE_USER");

        // 패스워드 인코더로 패스워드 암호화하기
        String enPw = passwordEncoder.encode(entity.getMemPwd());
        entity.setMemPwd(enPw);

        repository.save(entity);
        return true;
    }

    @Override
    public boolean login(MemberDTO dto) {
        Optional<Member> idOpt = repository.findById(dto.getMemId());

        if (idOpt.isPresent()) {
            Member member = idOpt.get();
            return passwordEncoder.matches(dto.getMemPwd(), member.getMemPwd());
        }
        return false;
    }

    @Override
    public MemberDTO readId(String id) {
        Optional<Member> result = repository.findById(id);
        if (result.isPresent()) {
            Member member = result.get();
            return entityToDto(member);
        }
        else {
            return null;
        }
    }

    @Override
    public MemberDTO readEmail(String email) {
        Optional<Member> result = repository.findById(email);
        if (result.isPresent()) {
            Member member = result.get();
            return entityToDto(member);
        }
        else {
            return null;
        }
    }

    // 아이디 중복 확인 메서드
    private boolean isIdDuplicate(String memId) {
        return repository.findById(memId).isPresent();
    }

    // 이메일 중복 확인 메서드
    private boolean isEmailDuplicate(String memEmail) {
        return repository.findByMemEmail(memEmail).isPresent();
    }

    // 멤버 등급 업데이트 메서드
    @Override
    public void updateMemberGrade(MemberDTO memberDTO) {

        memberDTO.updateGrade();
    }
}
