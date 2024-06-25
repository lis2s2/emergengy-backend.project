package project.emergency.member.entitiy;

import jakarta.persistence.*;
import lombok.*;
import project.emergency.base.entity.BaseEntity;

@Entity
@Table(name = "tbl_member")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @Column(length = 20, nullable = false)
    String memId;

    @Column(length = 20, nullable = false)
    String memPwd;

    @Column(length = 50, nullable = false)
    String memEmail;

    @Column(length = 20, nullable = false)
    String memName;

    @Column(length = 20, nullable = false)
    String memNick;

    @Column(length = 20, nullable = false)
    String memGrade;

    @Column(nullable = true)
    int memPoint;
}
