package project.emergency.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import project.emergency.member.dto.MemberDTO;
import project.emergency.member.entitiy.Member;
import project.emergency.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Service SecurityConfig에서 MemberService를 빈으로 등록했으면 중복 등록되므로 @Service 사용X
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

        if (dto.getMemId() == null || dto.getMemPwd() == null || dto.getMemName() == null || dto.getMemEmail() == null) {

            return false;
        }

        // 아이디나 이메일 중복 시 false 반환
        if (repository.existsById(dto.getMemId()) || repository.existsByMemEmail(dto.getMemEmail())) {

            return false;
        }

        Member entity = dtoToEntity(dto);

        // 기본 사이트 설정
        entity.setProvider("나지금급해");

        // 기본 등급 설정
        entity.setMemGrade("FAMILY");

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
    public boolean checkIdExists(String memId) {
        Optional<Member> checkId = repository.findById(memId);

        if (checkId.isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkEmailExists(String memEmail) {
        Optional<Member> checkEmail = repository.findByMemEmail(memEmail);

        if (checkEmail.isPresent()) {
            return false;
        }
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

        return result.map(this::entityToDto).orElse(null);
    }

    @Override
    public MemberDTO readEmail(String email) {

        Optional<Member> result = repository.findByMemEmail(email);

        if (result.isPresent()) {

            Member member = result.get();

            return entityToDto(member);
        } else {
            return null;
        }
    }

    @Override
    public String findid(String memName, String memEmail) {
        return repository.findId(memName, memEmail);
    }

    @Override
    public MemberDTO readPwd(String pwd) {

        Optional<Member> result = repository.findByMemPwd(pwd);

        if (result.isPresent()) {

            Member member = result.get();

            return entityToDto(member);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = false)
    @Override
    public boolean modifyMember(String memId, String memEmail, String memPwd) {

        Optional<Member> result = repository.findById(memId);

        if (result.isPresent()) {

            Member member = result.get();

            boolean modified = false;

            if (memEmail != null && !memEmail.isEmpty() && !memEmail.equals(member.getMemEmail())) {

                member.setMemEmail(memEmail);

                modified = true;
            }

            if (memPwd != null && !memPwd.isEmpty()) {

                member.setMemPwd(passwordEncoder.encode(memPwd));

                modified = true;
            }

            if (modified) {

                repository.save(member);

                return true;
            }
        }
        return false;
    }
}

    // 비밀번호 찾는 메소드
//     1.
//        @Override
//        public String findpwd(String memId, String memName, String memEmail) {
//            return repository.findPassword(memId, memName, memEmail);
//        }
//    2.
//    @Override
//    public String findpwd(MemberDTO dto) {
//        Optional<Member> pwd = repository.findByMemIdAndMemNameAndMemEmail(dto.getMemId(), dto.getMemName(), dto.getMemEmail());
//        return pwd.map(Member::getMemPwd).orElse(null);
//    }




