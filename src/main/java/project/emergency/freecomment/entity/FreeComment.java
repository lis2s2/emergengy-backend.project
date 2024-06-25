package project.emergency.freecomment.entity;

import jakarta.persistence.*;
import lombok.*;
import project.emergency.base.entity.BaseEntity;
import project.emergency.freeboard.entity.FreeBoard;
import project.emergency.member.entitiy.Member;

@Entity
@Table(name = "tbl_freecomment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreeComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int freecmtNo;

    @ManyToOne
    FreeBoard freeboardNo;

    @ManyToOne
    Member freeWriter;

    @Column(length = 200, nullable = false)
    String freeContent;
}
