package project.emergency.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.emergency.cart.dto.CartDTO;
import project.emergency.cart.service.CartService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    CartService service;

    @GetMapping
    public ResponseEntity<List<CartDTO>> serchCartlist() {
        List<CartDTO> list = service.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCart(@RequestBody CartDTO dto) {
        int no = service.addCart(dto);
        return ResponseEntity.ok("Cart added with ID: " + no);
    }
}
