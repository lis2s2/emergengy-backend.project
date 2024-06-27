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

    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody MemberDTO dto) {
        boolean result = service.register(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED); //201성공코드와 처리결과 반환
    }

    @GetMapping("/member/list")
    public ResponseEntity<List <MemberDTO>> getList() {
        List<MemberDTO> list = service.getList();
        return new ResponseEntity<>(list, HttpStatus.OK); //200성공코드와 회원목록 반환
    }

//    @GetMapping("/read") // 주소수정
//    public void read(@RequestParam(name = "id") String id, @RequestParam(name = "page", defaultValue = "0") int page, Model model) { //파라미터 추가
//        MemberDTO dto = service.read(id);
//        model.addAttribute("dto", dto);
//        model.addAttribute("page", page);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> read(@PathVariable String id) {
        MemberDTO dto = service.read(id);
        return dto != null ? new ResponseEntity<>(dto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // 200 성공 코드와 회원 정보 반환, 없으면 404 반환
    }
}
