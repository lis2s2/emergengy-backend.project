package project.emergency.cart.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import project.emergency.base.entity.BaseEntity;
import project.emergency.member.entitiy.Member;
import project.emergency.shop.entity.Shop;

@Entity
@Table(name = "tbl_cart")
@Where(clause = "is_deleted = false")
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
    Shop shop;

    @ManyToOne
//    @JoinColumn(name = "mem_id")
    Member member;

    @Column(nullable = false)
    int prodCount;

    @Column(name = "is_deleted", nullable = false)
    boolean isDeleted;
}

