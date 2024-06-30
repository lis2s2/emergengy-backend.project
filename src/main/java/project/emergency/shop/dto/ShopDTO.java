package project.emergency.shop.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopDTO {

    int no;
    String title;
    int price;
    String imgPath;

}
