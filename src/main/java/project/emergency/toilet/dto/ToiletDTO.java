package project.emergency.toilet.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToiletDTO {

    String toiletNo;
    Boolean memRegister;
    String writer;
    Double lat;
    Double log;
    String toiletAddress;
    String toiletName;
    String detail;
    Boolean disabled;
    Boolean diaper;
    Boolean separated;
    String status;

}
