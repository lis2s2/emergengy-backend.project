package project.emergency.order.service;

import org.springframework.stereotype.Service;
import project.emergency.member.entitiy.Member;
import project.emergency.order.dto.OrderDTO;
import project.emergency.order.entity.Order;
import project.emergency.orderItem.dto.OrderItemDTO;
import project.emergency.orderItem.entity.OrderItem;

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
        int totalAmount = entity.getOrderItems().stream()
                .mapToInt(item -> item.getProductPrice() * item.getCount())
                .sum();

        OrderDTO dto = OrderDTO.builder()
                .orderNo(entity.getOrderNo())
                .customerId(entity.getCustomer().getMemId())
                .usedPoint(entity.getUsedPoint())
                .totalAmount(totalAmount)
                .customerName(entity.getCustomerName())
                .address(entity.getAddress())
                .phoneNum(entity.getPhoneNum())
                .detailedAddress(entity.getDetailedAddress())
                .postalCode(entity.getPostalCode())
                .orderItems(entity.getOrderItems().stream()
                        .map(this::entityToDto)
                        .collect(Collectors.toList()))
                .build();

        return dto;
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
