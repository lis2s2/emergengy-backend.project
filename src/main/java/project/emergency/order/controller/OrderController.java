package project.emergency.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.emergency.order.dto.OrderDTO;
import project.emergency.order.service.OrderService;
import project.emergency.orderItem.dto.OrderItemDTO;
import project.emergency.orderItem.service.OrderItemService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    public OrderDTO saveOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @PostMapping("/items")
    public OrderItemDTO saveOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        return orderItemService.saveOrderItem(orderItemDTO);
    }

    @GetMapping("/{orderNo}")
    public OrderDTO getOrderDetails(@PathVariable int orderNo) {
        return orderService.getOrderDetails(orderNo);
    }

    @GetMapping("/items/{orderNo}")
    public List<OrderItemDTO> getOrderItems(@PathVariable int orderNo) {
        return orderItemService.findByOrderNo_OrderNo(orderNo)
                .stream()
                .map(orderItemService::entityToDto)
                .collect(Collectors.toList());
    }
}
