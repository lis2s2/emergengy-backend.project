package project.emergency.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.emergency.review.dto.ReviewDTO;
import project.emergency.review.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService service;

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody ReviewDTO dto) {
        service.register(dto);
        return new ResponseEntity<>(true, HttpStatus.CREATED); //201성공코드와 처리결과 반환
    }

    //localhost:8080/review?no=1
    @GetMapping("/detail")
    public ResponseEntity<List<ReviewDTO>> read(@RequestParam(name = "no") String no) {
        List<ReviewDTO> list = service.getByToiletNo(no);
        return new ResponseEntity<>(list, HttpStatus.OK); //200성공코드와 게시물정보를 반환한다
    }

}
