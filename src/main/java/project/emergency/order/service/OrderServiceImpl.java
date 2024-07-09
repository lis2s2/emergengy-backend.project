package project.emergency.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import project.emergency.order.dto.OrderDTO;
import project.emergency.order.entity.Order;
import project.emergency.order.repository.OrderRepository;
import project.emergency.orderItem.dto.OrderItemDTO;
import project.emergency.orderItem.entity.OrderItem;
import project.emergency.orderItem.repository.OrderItemRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public OrderDTO getOrderDetails(int orderNo) {

        // 주문 정보 조회
        Order order = orderRepository.findById(orderNo).orElseThrow(() -> new RuntimeException("Order not found"));

        // 주문 항목 정보 조회
        List<OrderItem> orderItems = orderItemRepository.findByOrderNo(orderNo);
        List<OrderItemDTO> orderItemDTOs = orderItems.stream().map(this::orderItemEntityToDto).collect(Collectors.toList());

        // Order를 OrderDTO로 변환하고, 주문 항목 리스트 추가
        OrderDTO orderDTO = entityToDto(order);
        orderDTO.setOrderItems(orderItemDTOs);

        return orderDTO;
    }
}
