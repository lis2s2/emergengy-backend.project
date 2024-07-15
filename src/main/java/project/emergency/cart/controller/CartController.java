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

    // 장바구니 목록 조회
    @GetMapping
    public ResponseEntity<List<CartDTO>> serchCartlist(@RequestParam(name = "id") String id) {
        List<CartDTO> list = service.getList(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 장바구니 추가
    @PostMapping("/add")
    public ResponseEntity<String> addCart(@RequestBody CartDTO dto) {
        int no = service.addCart(dto);
        return ResponseEntity.ok("장바구니가 성공적으로 담겼습니다. 장바구니 번호: " + no);
    }

    // 장바구니 삭제
    @PutMapping("/{cartNo}/delete")
    public ResponseEntity<String> deleteCart(@PathVariable int cartNo) {
        service.deleteCart(cartNo);
        return ResponseEntity.ok("장바구니 항목이 성공적으로 삭제되었습니다");
    }

    // 장바구니 상품 수량 변경
    @PutMapping("/{cartNo}/updateCount")
    public ResponseEntity<String> updateCartCount(@PathVariable int cartNo, @RequestParam int prodCount) {
        service.updateCartCount(cartNo, prodCount);
        return ResponseEntity.ok("장바구니 상품 수량이 성공적으로 업데이트되었습니다");
    }

    // 장바구니 수량 세기
    @GetMapping("/count")
    public long getCartCount(@RequestParam String memberMemId) {
        return service.getCartCountForUser(memberMemId);
    }

    // 장바구니 전체 삭제(회원별)
    @PutMapping("/deleteAll")
    public ResponseEntity<String> deleteAllCart(@RequestParam String memberMemId) {
        service.deleteAllCart(memberMemId);
        return ResponseEntity.ok("회원의 장바구니가 모두 삭제되었습니다");
    }

    // 선택된 장바구니 항목 삭제
    @PutMapping("/deleteSelected")
    public ResponseEntity<String> deleteSelectedCartItems(@RequestBody List<Integer> selectedItemIds) {
        service.deleteSelectedItems(selectedItemIds);
        return ResponseEntity.ok("선택된 장바구니 항목이 성공적으로 삭제되었습니다");
    }
}
