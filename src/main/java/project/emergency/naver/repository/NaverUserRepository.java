package project.emergency.naver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.emergency.member.entitiy.Member;

public interface NaverUserRepository extends JpaRepository<Member, String> {
}
