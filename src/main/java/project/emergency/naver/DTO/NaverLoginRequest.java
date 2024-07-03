package project.emergency.naver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

//@Getter
//@NoArgsConstructor
@AllArgsConstructor
public class NaverLoginRequest {

    private Map<String, Object> attributes;


    private String grantType;

    private String clientId;

    private String authorizationCode;

    private String state;
}
