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
    int productNo;

    @Column(length = 50, nullable = false)
    String productName;

    @Column(nullable = false)
    int productPoint;

    @Column(length = 200, nullable = false)
    String imgPath;
}
