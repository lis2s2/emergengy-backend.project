package project.emergency.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import project.emergency.member.dto.MemberDTO;
import project.emergency.member.service.MemberService;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    MemberService service;

    // 회원 등록
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
        System.out.println(exists);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    // 이메일 중복 체크
    @GetMapping("/register/checkemail")
    public ResponseEntity<Boolean> checkEmail(@RequestParam(name = "name") String memEmail) {
        boolean exists = service.checkEmailExists(memEmail);
        System.out.println(exists);
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
    public ResponseEntity<String> findPassword(@RequestBody MemberDTO dto) {
        String password = service.findid(dto.getMemName(), dto.getMemEmail());
        return password != null ? new ResponseEntity<>(password, HttpStatus.OK)
                : new ResponseEntity<>("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }

    // 수정
    @PutMapping("/mypage/modify")
    public ResponseEntity<Boolean> modify (@RequestBody MemberDTO dto) {
//    public ResponseEntity<Boolean> modify (@RequestParam(name = "id") String memId) {
//    public ResponseEntity<Boolean> modify (@RequestParam(name = "id") String memId, @RequestParam(name = "email") String memEmail, @RequestParam(name = "pw") String memPwd) {

        boolean result = service.modifyMember(dto.getMemId(),dto.getMemEmail(), dto.getMemPwd());
//        boolean result = service.modifyMember(memId, memEmail, memPwd);

        return new ResponseEntity<>(result, result? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    // 비밀번호 찾기
//    @PostMapping("/find/password")
//    public ResponseEntity<String> findPassword(@RequestBody MemberDTO dto) {
//        String password = service.findpwd(dto.getMemId(), dto.getMemName(), dto.getMemEmail());
//        return password != null ? new ResponseEntity<>(password, HttpStatus.OK)
//                : new ResponseEntity<>("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
//    }

    // 회원 목록
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
