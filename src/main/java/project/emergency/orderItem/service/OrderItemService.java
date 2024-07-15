package project.emergency.orderItem.service;

import org.springframework.stereotype.Service;
import project.emergency.order.entity.Order;
import project.emergency.orderItem.dto.OrderItemDTO;
import project.emergency.orderItem.entity.OrderItem;
import project.emergency.shop.entity.Shop;

import java.util.List;


public interface OrderItemService {

    OrderItemDTO saveOrderItem(OrderItemDTO orderItemDTO);

    List<OrderItem> findByOrderNo_OrderNo(int orderNo);

    default OrderItem dtoToEntity(OrderItemDTO dto) {
        Shop shop = Shop.builder().prodNo(dto.getProductNo()).build();
        Order order = Order.builder().orderNo(dto.getOrderNo()).build();

        return OrderItem.builder()
                .orderItemNo(dto.getOrderItemNo())
                .shop(shop)
                .order(order)
                .prodCount(dto.getCount())
                .build();

    }

    default OrderItemDTO entityToDto(OrderItem entity) {

         return OrderItemDTO.builder()
                 .orderItemNo(entity.getOrderItemNo())
                 .orderNo(entity.getOrder().getOrderNo())
                 .productNo(entity.getShop().getProdNo())
                 .count(entity.getProdCount())
                 .productPrice(entity.getShop().getProdPrice())
                 .totalPrice(entity.getProdCount() * entity.getShop().getProdPrice())
                 .build();
    }
}
