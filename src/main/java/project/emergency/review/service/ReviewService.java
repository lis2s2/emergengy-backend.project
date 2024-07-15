package project.emergency.review.service;

import project.emergency.member.entitiy.Member;
import project.emergency.review.dto.ReviewDTO;
import project.emergency.review.entity.Review;

import java.util.List;

public interface ReviewService {


    List<ReviewDTO> getByToiletNo(String toiletNo);
    void register(ReviewDTO dto);
    List<ReviewDTO> getList();
    List<ReviewDTO> getByWriter(String writer);
    double getToiletAvgScore(String toiletNo);

    default Review dtoToEntity(ReviewDTO dto) {
        Member member = Member.builder().memId(dto.getWriter()).build();
        return Review.builder()
                .reviewNo(dto.getReviewNo())
                .toiletNo(dto.getToiletNo())
                .reviewScore(dto.getReviewScore())
                .writer(member)
                .reviewContent(dto.getReviewContent())
                .isDeleted(dto.getIsDeleted())
                .toiletTitle(dto.getToiletTitle())
                .build();
    }

    default ReviewDTO entityToDto(Review entity) {

        return ReviewDTO.builder()
                .reviewNo(entity.getReviewNo())
                .toiletNo(entity.getToiletNo())
                .reviewScore(entity.getReviewScore())
                .writer(entity.getWriter().getMemId())
                .reviewContent(entity.getReviewContent())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .isDeleted(entity.getIsDeleted())
                .toiletTitle(entity.getToiletTitle())
                .build();
    }
}
