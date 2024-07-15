package project.emergency.orderItem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderItemDTO {
     int orderItemNo;
     int orderNo;
     int productNo;
     int count;
     int productPrice;
     int totalPrice;
//     String imgpath;
}
