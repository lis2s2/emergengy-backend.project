package project.emergency.helpcomment.entity;

import jakarta.persistence.*;
import lombok.*;
import project.emergency.base.entity.BaseEntity;
import project.emergency.freeboard.entity.FreeBoard;
import project.emergency.helpboard.entity.HelpBoard;
import project.emergency.member.entitiy.Member;

@Entity
@Table(name = "tbl_helpcomment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HelpComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int commentNo;

    @ManyToOne
    HelpBoard board;

    @ManyToOne
    Member writer;

    @Column(length = 200, nullable = false)
    String content;
}
