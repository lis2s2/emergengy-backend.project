package project.emergency.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.emergency.order.dto.OrderDTO;
import project.emergency.order.repository.OrderRepository;
import project.emergency.order.service.OrderService;
import project.emergency.orderItem.dto.OrderItemDTO;
import project.emergency.orderItem.repository.OrderItemRepository;
import project.emergency.orderItem.service.OrderItemService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderItemService orderItemService;

    OrderDTO orderDTO;

    @Test
    public void 주문추가테스트() {
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        orderItemDTOList.add(OrderItemDTO.builder()
                .productNo(1)
                .count(2)
                .productPrice(1000)
                .build());

        // 주문 생성
        orderDTO = OrderDTO.builder()
                .customerId("chacha")
                .usedPoint(100)
                .customerName("현아")
                .address("도림동")
                .phoneNum("01011111111")
                .detailedAddress("1동")
                .postalCode("12345")
                .orderItems(orderItemDTOList)
                .build();

    }

}
