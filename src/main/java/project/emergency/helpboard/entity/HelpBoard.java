package project.emergency.helpboard.entity;

import jakarta.persistence.*;
import lombok.*;
import project.emergency.base.entity.BaseEntity;
import project.emergency.member.entitiy.Member;

@Entity
@Table(name = "tbl_helpboard")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HelpBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int boardNo;

    @ManyToOne
    Member writer;

    @Column(length = 50, nullable = false)
    String title;

    @Column(length = 200, nullable = false)
    String content;

    @Column(nullable = true)
    int likes;
}
