package project.emergency.cart.entity;

import jakarta.persistence.*;
import lombok.*;
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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cartNo;

    @ManyToOne
    Shop cartProd;

    @ManyToOne
    Member customer;

    @Column(nullable = false)
    int prodCount;
}
