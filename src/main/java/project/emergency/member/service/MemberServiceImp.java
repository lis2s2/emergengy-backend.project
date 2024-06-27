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
        String id = dto.getMemId();
        MemberDTO getDtoId = read(id);

        String email = dto.getMemEmail();
        MemberDTO getDtoEmail = read(email);
        if (getDtoId != null) {
            System.out.println("사용중인 아이디입니다.");
            return false;
        } else if (getDtoEmail != null) {
            System.out.println("사용중인 이메일입니다.");
            return false;
        }

        Member entity = dtoToEntity(dto);

        // 기본 등급 설정
        entity.setMemGrade("Regular");

        // 패스워드 인코더로 패스워드 암호화하기
        String enPw = passwordEncoder.encode(entity.getMemPwd());
        entity.setMemPwd(enPw);

        repository.save(entity);
        return true;
    }

    @Override
    public MemberDTO read(String id) {
        Optional<Member> result = repository.findById(id);
        if (result.isPresent()) {
            Member member = result.get();
            return entityToDto(member);
        } else {
            return null;
        }
    }


    // 멤버 등급 업데이트 메서드
    @Override
    public void updateMemberGrade(MemberDTO memberDTO) {
        memberDTO.updateGrade();
    }
}
