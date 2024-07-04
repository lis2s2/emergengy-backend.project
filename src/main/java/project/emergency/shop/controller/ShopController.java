package project.emergency.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.emergency.member.entitiy.Member;
import project.emergency.shop.dto.ShopDTO;
import project.emergency.shop.service.ShopService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    ShopService service;

    @GetMapping
    public ResponseEntity<List<ShopDTO>> searchShops() {
        List<ShopDTO> list = service.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<ShopDTO> read(@RequestParam(name = "no") int no) {
        ShopDTO dto = service.read(no);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ShopDTO>> getByCategory(@RequestParam(name = "category") String category) {
        List<ShopDTO> list = service.getByCategory(category);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
