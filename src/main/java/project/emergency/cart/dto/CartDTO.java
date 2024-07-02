package project.emergency.cart.dto;

import lombok.*;
import project.emergency.member.entitiy.Member;
import project.emergency.shop.entity.Shop;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CartDTO {
    int no;
    Shop prodNo;
    Member memberId;
    int prodCount;
}
