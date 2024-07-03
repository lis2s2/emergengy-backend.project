package project.emergency.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emergency.member.entitiy.Member;
import project.emergency.review.dto.ReviewDTO;
import project.emergency.review.entity.Review;
import project.emergency.review.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository repository;


    @Override
    public List<ReviewDTO> getByToiletNo(String toiletNo) {
        List<Review> entityList = repository.findByToiletNo(toiletNo);
        return entityList.stream().map(this::entityToDto).toList();
    }

    @Override
    public void register(ReviewDTO dto) {
        Review entity = dtoToEntity(dto);
        repository.save(entity);
    }

    @Override
    public List<ReviewDTO> getList() {
        List<Review> entityList = repository.findAll();
        return entityList.stream().map(this::entityToDto).toList();
    }

    @Override
    public List<ReviewDTO> getByWriter(String writer) {
        Member member = Member.builder().memId(writer).build();
        List<Review> entityList = repository.findByWriter(member);
        return entityList.stream().map(this::entityToDto).toList();
    }

    @Override
    public double getToiletAvgScore(String toiletNo) {
        List<Review> entityList = repository.findByToiletNo(toiletNo);
        double averageScore = entityList.stream()
                .mapToDouble(Review::getReviewScore)
                .average()
                .orElse(3.0);
        return Math.round(averageScore * 10) / 10.0;
    }
}
