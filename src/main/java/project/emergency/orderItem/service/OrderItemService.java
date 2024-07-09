package project.emergency.orderItem.service;

import org.springframework.stereotype.Service;
import project.emergency.order.entity.Order;
import project.emergency.orderItem.dto.OrderItemDTO;
import project.emergency.orderItem.entity.OrderItem;
import project.emergency.shop.entity.Shop;

import java.util.List;

@Service
public interface OrderItemService {
    // 주문번호에 따른 모든 주문 항목 조회
    List<OrderItem> findByOrderNo_OrderNo(int orderNo);

    default OrderItem dtoToEntity(OrderItemDTO dto) {
        Shop shop = Shop.builder().prodNo(dto.getProductNo()).build();
        Order order = Order.builder().orderNo(dto.getOrderNo()).build();

        return OrderItem.builder()
                .orderItemNo(dto.getOrderItemNo())
                .productNo(shop)

                .build();
    }
}
