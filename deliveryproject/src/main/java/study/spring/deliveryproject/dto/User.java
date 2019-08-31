package study.spring.deliveryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int idx;
    private String name;
    private String email;
    private String profileUrl;
    private String nickname;
    private String phoneNum;
}
