package project.emergency.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.emergency.member.entitiy.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    // 이메일 찾는 메소드
    // 엔티티 변수명이랑 동일하게 함수명도 적어야 함
    boolean existsByMemEmail(String memEmail);
    Optional<Member> findByMemEmail(String memEmail);

}
