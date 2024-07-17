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
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    //localhost:8080/review/list/byToiletNo?no=1
    @GetMapping("/list/byToiletNo")
    public ResponseEntity<List<ReviewDTO>> readByToiletNo(@RequestParam(name = "no") String no) {
        List<ReviewDTO> list = service.getByToiletNo(no);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //localhost:8080/review/list/byWriter?writer=1
    @GetMapping("/list/byWriter")
    public ResponseEntity<List<ReviewDTO>> readByWriter(@RequestParam(name = "writer") String writer) {
        List<ReviewDTO> list = service.getByWriter(writer);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //localhost:8080/review/score?no=1
    @GetMapping("/score")
    public ResponseEntity<Double> readAvgScore(@RequestParam(name = "no") String no) {
        double score = service.getToiletAvgScore(no);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<Boolean> deleteReviewByNo(@RequestParam(name = "no") int reviewNo) {
        service.deleteById(reviewNo);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
