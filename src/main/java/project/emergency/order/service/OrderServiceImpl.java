package project.emergency.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emergency.order.dto.OrderDTO;
import project.emergency.order.entity.Order;
import project.emergency.order.repository.OrderRepository;
import project.emergency.orderItem.dto.OrderItemDTO;
import project.emergency.orderItem.entity.OrderItem;
import project.emergency.orderItem.repository.OrderItemRepository;
import project.emergency.shop.entity.Shop;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public OrderDTO getOrderDetails(int orderNo) {
        Order order = orderRepository.findById(orderNo)
                .orElseThrow(() -> new RuntimeException("주문을 가져오는 중 오류 발생"));
        return entityToDto(order);
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = dtoToEntity(orderDTO);
        order = orderRepository.save(order);

        // OrderItems 저장하기
        for (OrderItemDTO orderItemDTO : orderDTO.getOrderItems()) {
            OrderItem orderItem = dtoToEntity(orderItemDTO);
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }

        return entityToDto(order);
    }

    private OrderItem dtoToEntity(OrderItemDTO dto) {
        Shop shop = Shop.builder().prodNo(dto.getProductNo()).build();
        Order order = Order.builder().orderNo(dto.getOrderNo()).build();

        return OrderItem.builder()
                .orderItemNo(dto.getOrderItemNo())
                .shop(shop)
                .order(order)
                .count(dto.getCount())
                .productPrice(dto.getProductPrice())
                .build();
    }
}
