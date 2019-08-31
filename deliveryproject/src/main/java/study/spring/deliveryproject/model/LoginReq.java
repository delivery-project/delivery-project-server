package study.spring.deliveryproject.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
public class LoginReq {
    private String email;
    private String password;
}
