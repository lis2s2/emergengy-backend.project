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
    // 소셜 로그인 시에 문자 길이가 길어지기에 길이 넉넉하게 설정
    @Column(length = 100, nullable = false)
    String memId;

    // 암호화 시에 문자 길이가 길어지기에 길이 넉넉하게 설정
    @Column(length = 100, nullable = false)
    String memPwd;

    @Column(length = 50, nullable = false)
    String memEmail;

    @Column(length = 20, nullable = false)
    String memName;

    @Column
    String provider;

    @Column
    String providerId;

//    @Column(length = 20, nullable = false)
//    String memNick;

    @Column(length = 20, nullable = false)
    String memGrade;

    @Column(length = 100, nullable = false)
    String memRole;

    @Column(nullable = true)
    int memPoint;
}
