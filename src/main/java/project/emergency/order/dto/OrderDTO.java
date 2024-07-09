package project.emergency.order.dto;

import lombok.*;
import project.emergency.orderItem.dto.OrderItemDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDTO {
    int orderNo;
    String customerId;
    int usedPoint;
    int totalAmount;
    String customerName;
    String address;
    String phoneNum;
    String detailedAddress;
    String postalCode;
    List<OrderItemDTO> orderItems;
}
