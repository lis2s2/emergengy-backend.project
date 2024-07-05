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
    int prodNo;
    String memberId;
    int prodCount;
    int prodPrice;
    String prodImgpath;
    String prodTitle;
    boolean isDeleted;
}
