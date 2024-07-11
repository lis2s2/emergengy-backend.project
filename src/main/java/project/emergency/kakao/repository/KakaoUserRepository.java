package project.emergency.kakao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.emergency.member.entitiy.Member;

public interface KakaoUserRepository extends JpaRepository<Member, String> {
}
