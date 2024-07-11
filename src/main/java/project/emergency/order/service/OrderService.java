package project.emergency.order.service;

import org.springframework.stereotype.Service;
import project.emergency.member.entitiy.Member;
import project.emergency.order.dto.OrderDTO;
import project.emergency.order.entity.Order;
import project.emergency.orderItem.dto.OrderItemDTO;
import project.emergency.orderItem.entity.OrderItem;

import java.util.ArrayList;
import java.util.stream.Collectors;


public interface OrderService {

    OrderDTO getOrderDetails(int orderNo);

    OrderDTO saveOrder(OrderDTO orderDTO);

    default Order dtoToEntity(OrderDTO dto) {
        Member member = Member.builder().memId(dto.getCustomerId()).build();

        Order entity = Order.builder()
                .orderNo(dto.getOrderNo())
                .address(dto.getAddress())
                .phoneNum(dto.getPhoneNum())
                .customer(member)
                .customerName(dto.getCustomerName())
                .detailedAddress(dto.getDetailedAddress())
                .postalCode(dto.getPostalCode())
                .usedPoint(dto.getUsedPoint())
                .totalAmount(dto.getTotalAmount())
                .build();

        return entity;
    }

    default  OrderDTO entityToDto(Order entity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNo(entity.getOrderNo());
        orderDTO.setCustomerName(entity.getCustomerName());
        orderDTO.setAddress(entity.getAddress());
        orderDTO.setPhoneNum(entity.getPhoneNum());
        orderDTO.setDetailedAddress(entity.getDetailedAddress());
        orderDTO.setPostalCode(entity.getPostalCode());
        orderDTO.setUsedPoint(entity.getUsedPoint());
        orderDTO.setTotalAmount(entity.getTotalAmount());

        if (entity.getOrderItems() != null) {
            orderDTO.setOrderItems(entity.getOrderItems().stream()
                    .map(this::entityToDto)
                    .collect(Collectors.toList()));
        } else {
            orderDTO.setOrderItems(new ArrayList<>());
        }

        return orderDTO;
    }

    default OrderItemDTO entityToDto(OrderItem entity) {
        return OrderItemDTO.builder()
                .orderItemNo(entity.getOrderItemNo())
                .orderNo(entity.getOrder().getOrderNo())
                .productNo(entity.getShop().getProdNo())
                .count(entity.getCount())
                .productPrice(entity.getProductPrice())
                .totalPrice(entity.getCount() * entity.getProductPrice())
                .build();
    }
}
