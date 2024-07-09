package project.emergency.order.service;

import project.emergency.member.entitiy.Member;
import project.emergency.order.dto.OrderDTO;
import project.emergency.order.entity.Order;
import project.emergency.orderItem.dto.OrderItemDTO;
import project.emergency.orderItem.entity.OrderItem;

public interface OrderService {

    OrderDTO getOrderDetails(int orderNo);

    default OrderItemDTO orderItemEntityToDto(OrderItem entity) {
        return OrderItemDTO.builder()
                .orderItemNo(entity.getOrderItemNo())
                .orderNo(entity.getOrderNo().getOrderNo())
                .productNo(entity.getProductNo().getProdNo())
                .count(entity.getCount())
                .productPrice(entity.getProductPrice())
                .totalPrice(entity.getProductPrice() * entity.getCount())
                .build();
    }

    default Order dtoToEntity(OrderDTO dto) {
        Member member = Member.builder().memId(dto.getCustomerId()).build();

        return Order.builder()
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
    }

    default  OrderDTO entityToDto(Order entity) {
        return OrderDTO.builder()
                .orderNo(entity.getOrderNo())
                .customerId(entity.getCustomer().getMemId())
                .usedPoint(entity.getUsedPoint())
                .totalAmount(entity.getTotalAmount())
                .customerName(entity.getCustomerName())
                .address(entity.getAddress())
                .phoneNum(entity.getPhoneNum())
                .detailedAddress(entity.getDetailedAddress())
                .postalCode(entity.getPostalCode())
                .build();
    }
}
