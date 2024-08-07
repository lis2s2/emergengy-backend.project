package project.emergency.toilet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.emergency.toilet.dto.ToiletDTO;
import project.emergency.toilet.service.ToiletService;

import java.util.List;

@RestController
@RequestMapping("/toilet")
public class ToiletController {

    @Autowired
    ToiletService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ToiletDTO dto) {
        String result = service.register(dto);
        return new ResponseEntity<String>(result, HttpStatus.CREATED);
    }

    @PostMapping("/registerInfo")
    public ResponseEntity<String> registerInfo(@RequestBody ToiletDTO dto) {
        String result = service.registerInfo(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ToiletDTO>> readAll() {
        List<ToiletDTO> list = service.getListAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/list/byToiletNo")
    public ResponseEntity<ToiletDTO> readByToiletNo(@RequestParam(name = "no") String no) {
        ToiletDTO dto = service.getByToiletNo(no);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/list/byMemRegister")
    public ResponseEntity<List<ToiletDTO>> readByMemRegister() {
        List<ToiletDTO> list = service.getListByMemRegister();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 여기서부터 사용X
    @GetMapping("/list/byDisabled")
    public ResponseEntity<List<ToiletDTO>> readByDisabled() {
        List<ToiletDTO> list = service.getListByDisabled();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/list/byDiaper")
    public ResponseEntity<List<ToiletDTO>> readByDiaper() {
        List<ToiletDTO> list = service.getListByDiaper();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/list/bySeparated")
    public ResponseEntity<List<ToiletDTO>> readBySeparated() {
        List<ToiletDTO> list = service.getListBySeparated();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/list/byPaper")
    public ResponseEntity<List<ToiletDTO>> readByPaper() {
        List<ToiletDTO> list = service.getListByPaper();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



}
