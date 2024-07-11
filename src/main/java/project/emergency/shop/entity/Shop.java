package project.emergency.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import project.emergency.base.entity.BaseEntity;

@Entity
@Table(name = "tbl_shop")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int prodNo;

    @Column(length = 50, nullable = false)
    String prodName;

    @Column(nullable = false)
    int prodPrice;

    @Column(length = 200, nullable = false)
    String prodImgpath;

    @Column(length = 300)
    String prodDetailimgpath;

    @Column(length = 20, nullable = false)
    String prodCategory;
}
