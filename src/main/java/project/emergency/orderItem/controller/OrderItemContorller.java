package project.emergency.orderItem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.emergency.orderItem.dto.OrderItemDTO;
import project.emergency.orderItem.service.OrderItemService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderitems")
public class OrderItemContorller {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        return ResponseEntity.ok(orderItemService.saveOrderItem(orderItemDTO));
    }

    @GetMapping("/{orderNo}")
    public ResponseEntity<List<OrderItemDTO>> getOrderItemsByOrderNo(@PathVariable int orderNo) {
        return ResponseEntity.ok(orderItemService.findByOrderNo_OrderNo(orderNo)
                .stream()
                .map(orderItemService::entityToDto)
                .collect(Collectors.toList()));
    }
}
