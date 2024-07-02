package project.emergency.cart.entity;

import jakarta.persistence.*;
import lombok.*;
import project.emergency.base.entity.BaseEntity;
import project.emergency.member.entitiy.Member;
import project.emergency.shop.entity.Shop;

@Entity
@Table(name = "tbl_cart")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cartNo;

    @ManyToOne
    @JoinColumn(name = "prod_no")
    Shop Shop;

    @ManyToOne
    @JoinColumn(name = "mem_id")
    Member Member;

    @Column(nullable = false)
    int prodCount;
}
