package project.emergency.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.emergency.member.entitiy.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
