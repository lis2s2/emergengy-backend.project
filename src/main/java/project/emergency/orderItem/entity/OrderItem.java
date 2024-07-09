package project.emergency.orderItem.entity;

import jakarta.persistence.*;
import lombok.*;
import project.emergency.order.entity.Order;
import project.emergency.shop.entity.Shop;

@Entity
@Table(name = "tbl_order_item")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    // 쓸모없는 pk
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int orderItemNo;

    // 주문번호(받아오기)
    @ManyToOne
    @Column(nullable = false)
    Order orderNo;

    // 상품
    @ManyToOne
    @Column(nullable = false)
    Shop productNo;

    // 상품주문수량
    @Column(nullable = false)
    int count;

    // 상품가격
    @Column(nullable = false)
    int productPrice;

}
