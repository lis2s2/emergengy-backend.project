package project.emergency.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.emergency.member.entitiy.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    // 엔티티 변수명이랑 동일하게 함수명도 적어야 함
    boolean existsByMemEmail(String memEmail); // 이메일 찾는 메소드
    Optional<Member> findByMemEmail(String memEmail);

    boolean existsById(String memId); // 아이디 찾는 메소드

    Optional<Member> findByMemPwd(String memPwd);

    @Query("SELECT m.memId FROM Member m WHERE m.memName = :memName AND m.memEmail = :memEmail")
    String findId(@Param("memName") String memName, @Param("memEmail") String memEmail); // 아이디 찾는 메소드

    // 비밀번호 찾기 관련 쿼리
//    Optional<Member> findByMemId( String memName, String memEmail);
//    Optional<Member> findByMemIdAndMemNameAndMemEmail(String memId, String memName, String memEmail);
//    @Query("SELECT m.memPwd FROM Member m WHERE m.memId = :memId AND m.memName = :memName AND m.memEmail = :memEmail")
//    String findPassword(@Param("memId") String memId, @Param("memName") String memName, @Param("memEmail") String memEmail);

}
