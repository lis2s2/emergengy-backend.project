package project.emergency.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.emergency.member.dto.MemberDTO;
import project.emergency.member.service.MemberService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody MemberDTO dto) {
        if (dto.getMemId() == null || dto.getMemPwd() == null) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST); // 400 오류 코드 반환
        }
        boolean result = service.login(dto);
        return result ? new ResponseEntity<>(true, HttpStatus.OK) // 200 성공 코드와 로그인 정보 반환
                : new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

    // 회원 목록
    @GetMapping("/member/list")
    public ResponseEntity<List <MemberDTO>> getList() {
        List<MemberDTO> list = service.getList();
        return new ResponseEntity<>(list, HttpStatus.OK); // 200성공코드와 회원목록 반환
    }

    //   @GetMapping: 요청 url에 대한 GET 요청을 메소드와 mapping시키는 것
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

//    @GetMapping("/read") // 주소수정
//    public void read(@RequestParam(name = "id") String id, @RequestParam(name = "page", defaultValue = "0") int page, Model model) { //파라미터 추가
//        MemberDTO dto = service.read(id);
//        model.addAttribute("dto", dto);
//        model.addAttribute("page", page);
//    }
