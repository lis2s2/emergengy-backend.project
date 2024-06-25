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
    int helpboardNo;

    @ManyToOne
    Member helpwriter;

    @Column(length = 50, nullable = false)
    String helpTitle;

    @Column(length = 200, nullable = false)
    String helpContent;

    @Column(nullable = true)
    int helpLikes;
}
