package project.emergency.order.entity;

import jakarta.persistence.*;
import lombok.*;
import project.emergency.base.entity.BaseEntity;
import project.emergency.member.entitiy.Member;
import project.emergency.orderItem.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_order")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {

    // 주문번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int orderNo;

    // 멤버id
    @ManyToOne
    @JoinColumn(name = "memId", nullable = false)
    Member member;

    // 사용한 포인트
    @Column(nullable = false)
    int usedPoint;

    // 총 결제금액
    // for문 돌려서 ( count * price )
    @Column(nullable = false)
    int totalAmount;

    // 수령인
    @Column(length = 50, nullable = false)
    String customerName;

    // 주소
    @Column(length = 200, nullable = false)
    String address;

    // 연락처
    @Column(length = 50, nullable = false)
    String phoneNum;

    // 상세주소
    @Column(length = 100, nullable = false)
    String detailedAddress;

    // 우편번호
    @Column(length = 6, nullable = false)
    String postalCode;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderItem> orderItems = new ArrayList<>();

}
