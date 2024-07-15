package project.emergency.review.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

    int reviewNo;
    String toiletNo;
    String writer;
    String reviewContent;
    int reviewScore;
    LocalDateTime regDate;
    LocalDateTime modDate;
    String toiletTitle;
    Boolean isDeleted;

}
