package project.emergency.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emergency.order.dto.OrderDTO;
import project.emergency.order.entity.Order;
import project.emergency.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{


    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderDTO getOrderDetails(int orderNo) {
        Order order = orderRepository.findById(orderNo)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return entityToDto(order);
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = dtoToEntity(orderDTO);
        order = orderRepository.save(order);
        return entityToDto(order);
    }
}
