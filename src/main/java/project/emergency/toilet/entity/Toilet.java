package project.emergency.toilet.entity;

import jakarta.persistence.*;
import lombok.*;
import project.emergency.base.entity.BaseEntity;
import project.emergency.member.entitiy.Member;

@Entity
@Table(name = "tbl_toilet")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Toilet extends BaseEntity {

    @Id
    String toiletNo;

    @Column
    Boolean memRegister;

    @ManyToOne
    Member writer;

    @Column(length = 20, nullable = false)
    Double lat;

    @Column(length = 20, nullable = false)
    Double lng;

    @Column(length = 20, nullable = false)
    String toiletName;

    @Column(length = 50, nullable = false)
    String toiletAddress;

    @Column(length = 20)
    String detail;

    @Column
    Boolean disabled;

    @Column
    Boolean diaper;

    @Column
    Boolean separated;

    @Column
    Boolean paper;

    @Column(length = 20, nullable = false)
    String toiletStatus;



}
