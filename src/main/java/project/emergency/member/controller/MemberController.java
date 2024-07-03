package project.emergency.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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

//    @GetMapping("/auth/oauth2/code/kakao")
//    public String loginByKakao(@RequestParam final String code) {
//
//        System.out.println("code = " + code);
//
//        // 1. header 생성
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
//
//        // 2. body 생성
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code"); //고정값
//        params.add("client_id", "REST_API_KEY 입력");
//        params.add("redirect_uri", "http://localhost:3000/oauth2/code/kakao"); //등록한 redirect uri
//        params.add("code", code);
//
//        // 3. header + body
//        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);
//
//        // 4. http 요청하기
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Object> response = restTemplate.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                httpEntity,
//                Object.class
//        );
//
//        System.out.println("response = " + response);
//
//        return "ok";
//    }

//    @GetMapping("/login/oauth2/code/kakao?code={code}" )
//    public String getAccessToken(@RequestParam("code") String code) {
//        System.out.println("code = " + code);
//
//        // 1. header 생성
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
//
//        // 2. body 생성
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code"); //고정값
//        params.add("client_id", "REST_API_KEY 입력");
//        params.add("redirect_uri", "http://localhost:3000/oauth2/code/kakao"); //등록한 redirect uri
//        params.add("code", code);
//
//        // 3. header + body
//        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);
//
//        // 4. http 요청하기
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Object> response = restTemplate.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                httpEntity,
//                Object.class
//        );
//
//        System.out.println("response = " + response);
//
//        return "home";
//    }

    // 회원 목록
    @GetMapping("/member/list")
    public ResponseEntity<List<MemberDTO>> getList() {
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
