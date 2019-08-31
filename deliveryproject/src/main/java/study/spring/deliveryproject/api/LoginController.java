package study.spring.deliveryproject.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.deliveryproject.model.DefaultRes;
import study.spring.deliveryproject.model.LoginReq;
import study.spring.deliveryproject.service.AuthService;
import study.spring.deliveryproject.utils.ResponseMessage;
import study.spring.deliveryproject.utils.StatusCode;

@Slf4j
@RestController
@RequestMapping("auth")
public class LoginController {
    private static final DefaultRes FAIL_DEFAULT_RES = new DefaultRes(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody final LoginReq loginReq){
        try{
            return new ResponseEntity(authService.login(loginReq), HttpStatus.OK);
        }catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
