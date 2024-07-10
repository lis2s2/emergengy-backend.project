package project.emergency.orderItem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emergency.orderItem.dto.OrderItemDTO;
import project.emergency.orderItem.entity.OrderItem;
import project.emergency.orderItem.repository.OrderItemRepository;
import project.emergency.orderItem.service.OrderItemService;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public OrderItemDTO saveOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = dtoToEntity(orderItemDTO);
        orderItem = orderItemRepository.save(orderItem);
        return entityToDto(orderItem);
    }

    @Override
    public List<OrderItem> findByOrderNo_OrderNo(int orderNo) {
        return orderItemRepository.findByOrderOrderNo(orderNo);
    }
}
