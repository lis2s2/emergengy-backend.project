package project.emergency.freeboard.entity;

import jakarta.persistence.*;
import lombok.*;
import project.emergency.base.entity.BaseEntity;
import project.emergency.member.entitiy.Member;

@Entity
@Table(name = "tbl_freeboard")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreeBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int freeboardNo;

    @ManyToOne
    Member freeWriter;

    @Column(length = 50, nullable = false)
    String freeTitle;

    @Column(length = 200, nullable = false)
    String freeContent;

    @Column(nullable = true)
    int freeLikes;
}
