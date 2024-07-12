package project.emergency.member.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import project.emergency.member.dto.MailDto;
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

    @Autowired
    private MailService mailService;

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
//        entity.setProvider("나지금급해");

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

    @Override
    public boolean deleteMember(String memId) {

        Optional<Member> result = repository.findById(memId);

        if (result.isPresent()) {

            repository.deleteById(memId);

            return true;
        }
        return false;
    }

//    @Override
//    public MemberDTO readPwd(String pwd) {
//
//        Optional<Member> result = repository.findByMemPwd(pwd);
//
//        if (result.isPresent()) {
//
//            Member member = result.get();
//
//            return entityToDto(member);
//        } else {
//            return null;
//        }
//    }

        @Override
        public String findpwd(MemberDTO dto) {
//        public String findpwd(String memId, String memName, String memEmail) {

            Member member = repository.findByMemEmail(dto.getMemEmail()).orElse(null);

            // 임시 비밀번호 생성
            String tempPwd = getTempPassword();
            // 임시 비밀번호 encoding
            String encodedTempPwd = passwordEncoder(tempPwd);
            // member의 비밀번호 업데이트
            member.setMemPwd(encodedTempPwd );

            // 메일 생성
            MailDto mailDto = new MailDto();
            mailDto.setSender("hyeon6895@naver.com");
            mailDto.setReceiver(dto.getMemEmail());
            mailDto.setTitle("나지금급해 홈페이지 임시비밀번호 안내");
            mailDto.setMessage("안녕하세요 나지금급해입니다." + member.getMemName() + " 회원님의 임시 비밀번호는 " + tempPwd + " 입니다.");

            // 메일 송부
//            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//
//            try{
//                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
//                mimeMessageHelper.setTo(mailDto.getReceiver());
//                mimeMessageHelper.setSubject(mailDto.getTitle());
//                mimeMessageHelper.setText(mailDto.getMessage(), false);
//                javaMailSender.send(mimeMessage);
//
//                return String.valueOf(dto);
//
//
//            } catch (MessagingException e) {
//                throw new RuntimeException(e);
//            }
//            try {
//                mailService.sendMail(mailDto);
//            } catch (MessagingException e) {
//                throw new RuntimeException(e);
//            }
            try {
                mailService.sendMail(mailDto);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            repository.save(member);

            return "임시 비밀번호가 이메일로 전송되었습니다.";

//            return tempPwd;
        }

    // 존재하는 member일 경우 임시 비번 생성
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        StringBuilder tempPwd = new StringBuilder();

        int index = 0;

        for (int i = 0; i < 10; i++) {

            index = (int) (charSet.length * Math.random());

            tempPwd.append(charSet[index]);
        }

        return tempPwd.toString();
    }

    // 임시로 생성한 비번 encoding
    public String passwordEncoder(String tempPwd){

        return passwordEncoder.encode(tempPwd);
    }
}
////    2.
////    @Override
////    public String findpwd(MemberDTO dto) {
////        Optional<Member> pwd = repository.findByMemIdAndMemNameAndMemEmail(dto.getMemId(), dto.getMemName(), dto.getMemEmail());
////        return pwd.map(Member::getMemPwd).orElse(null);
////    }







