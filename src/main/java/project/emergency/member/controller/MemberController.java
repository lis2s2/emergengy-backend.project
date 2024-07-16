package project.emergency.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.emergency.member.dto.MailDto;
import project.emergency.member.dto.MemberDTO;
import project.emergency.member.service.MailService;
import project.emergency.member.service.MemberService;
import project.emergency.review.dto.ReviewDTO;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    MemberService service;

    @Autowired
    MailService mailService;

    // 회원 가입
    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody MemberDTO dto) {
        boolean result = service.register(dto);

        return new ResponseEntity<>(result, HttpStatus.CREATED); //201성공코드와 처리결과 반환
    }

    // 아이디 중복 체크
    @GetMapping("/register/checkid")
    public ResponseEntity<Boolean> checkId(@RequestParam(name = "name") String memId) {
        boolean exists = service.checkIdExists(memId);

        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // 이메일 중복 체크
    @GetMapping("/register/checkemail")
    public ResponseEntity<Boolean> checkEmail(@RequestParam(name = "name") String memEmail) {
        boolean exists = service.checkEmailExists(memEmail);

        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // 로그인
    // 나중에 포스트매핑 하지 않아도 /login을 시큐리티콘피그에서 낚아채는 거 해보기!
    @PostMapping("/login/**")
    public ResponseEntity<Boolean> login(@RequestParam MemberDTO dto) {
        if (dto.getMemId() == null || dto.getMemPwd() == null) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST); // 400 오류 코드 반환
        }
        boolean result = service.login(dto);

        return result ? new ResponseEntity<>(true, HttpStatus.OK) // 200 성공 코드와 로그인 정보 반환
                : new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

    // 아이디 찾기
    @PostMapping("/find/id")
    public ResponseEntity<String> findId(@RequestBody MemberDTO dto) {
        String password = service.findid(dto.getMemName(), dto.getMemEmail());

        return password != null ? new ResponseEntity<>(password, HttpStatus.OK)
                : new ResponseEntity<>("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }

    // 비밀번호 찾기
    @PostMapping("/find/pwd")
    public ResponseEntity<String> findPassword(@RequestBody MemberDTO dto) {
        String result = service.findpwd(dto.getMemId(), dto.getMemName(), dto.getMemEmail());

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found");
        }
    }

    // 회원 정보 수정
    @PutMapping("/mypage/modify")
    public ResponseEntity<Boolean> modify (@RequestBody MemberDTO dto) {
        boolean result = service.modifyMember(dto.getMemId(),dto.getMemEmail(), dto.getMemPwd());

        return new ResponseEntity<>(result, result? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/member/byId")
    public ResponseEntity<MemberDTO> readByWriter(@RequestParam(name = "id") String id) {
        MemberDTO dto = service.readId(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/delete-member/{memId}")
    public ResponseEntity<Boolean> deleteMember(@PathVariable String memId) {
        boolean result = service.deleteMember(memId);

        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    // 회원 목록 조회
    @GetMapping("/member/list")
    public ResponseEntity<List<MemberDTO>> getList() {
        List<MemberDTO> list = service.getList();

        return new ResponseEntity<>(list, HttpStatus.OK); // 200성공코드와 회원목록 반환
    }

    //  @GetMapping: 요청 url에 대한 GET 요청을 메소드와 mapping시키는 것
    @GetMapping("/id/{id}")
    public ResponseEntity<MemberDTO> readId(@PathVariable String id) {
        MemberDTO dto = service.readId(id);

        return dto != null ? new ResponseEntity<>(dto, HttpStatus.OK) // 200 성공 코드와 회원 정보 반환,
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<MemberDTO> readEmail(@PathVariable String email) {
        MemberDTO dto = service.readEmail(email);

        return dto != null ? new ResponseEntity<>(dto, HttpStatus.OK) // 200 성공 코드와 회원 정보 반환
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
