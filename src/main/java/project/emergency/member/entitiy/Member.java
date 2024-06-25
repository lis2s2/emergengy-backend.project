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
    @Column(length = 50, nullable = false)
    String id;

    @Column(length = 30, nullable = false)
    String password;

    @Column(length = 50, nullable = false)
    String email;

    @Column(length = 20, nullable = false)
    String name;

    @Column(length = 20, nullable = false)
    String nickName;

    @Column(length = 20, nullable = false)
    String role;

    @Column(nullable = true)
    int point;
}
