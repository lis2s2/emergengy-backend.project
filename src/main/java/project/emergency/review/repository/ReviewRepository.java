package project.emergency.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.emergency.member.entitiy.Member;
import project.emergency.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // 엔티티 변수명이랑 동일하게 함수명도 적어야 함
    List<Review> findByToiletNo(String toiletNo);
    List<Review> findByWriter(Member writer);
    void deleteByReviewNo(int reviewNo);
}
