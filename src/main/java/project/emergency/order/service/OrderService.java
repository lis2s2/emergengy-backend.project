package project.emergency.order.service;

import project.emergency.order.dto.OrderDTO;



public interface OrderService {

    OrderDTO getOrderDetails(int orderNo);
    OrderDTO saveOrder(OrderDTO orderDTO);

}
