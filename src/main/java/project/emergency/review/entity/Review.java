package project.emergency.review.entity;

import jakarta.persistence.*;
import lombok.*;
import project.emergency.base.entity.BaseEntity;
import project.emergency.member.entitiy.Member;

@Entity
@Table(name = "tbl_review")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int reviewNo;

    @Column(length = 50, nullable = false)
    String toiletNo;

    @ManyToOne
    Member writer;

    @Column(length = 500, nullable = false)
    String reviewContent;

    @Column(length = 10, nullable = false)
    int reviewScore;

    @Column(length = 50, nullable = false)
    String toiletTitle;

    @Column
    Boolean isDeleted;


}
