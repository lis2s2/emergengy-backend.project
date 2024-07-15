package project.emergency.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.emergency.member.entitiy.Member;

import java.util.Optional;

// 엔티티 변수명이랑 동일하게 함수명도 적어야 함
public interface MemberRepository extends JpaRepository<Member, String> {
    boolean existsByMemEmail(String memEmail); // 이메일 찾기
    Optional<Member> findByMemEmail(String memEmail);

    boolean existsById(String memId); // 아이디 찾기

    @Query("SELECT m.memId FROM Member m WHERE m.memName = :memName AND m.memEmail = :memEmail")
    String findId(@Param("memName") String memName, @Param("memEmail") String memEmail); // 아이디 찾는 메소드

    @Query("SELECT m.memPwd FROM Member m WHERE m.memId = :memId AND m.memName = :memName AND m.memEmail = :memEmail")
    String findPwd(@Param("memId") String memId, @Param("memName") String memName, @Param("memEmail") String memEmail); // 비밀번호 찾기

    Optional<Member> findByMemIdAndMemNameAndMemEmail(String memId, String memName, String memEmail);
}
