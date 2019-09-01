package study.spring.deliveryproject.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.spring.deliveryproject.model.SignUpReq;
import study.spring.deliveryproject.service.UserService;


import static study.spring.deliveryproject.model.DefaultRes.FAIL_DEFAULT_RES;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {
    private static final String HEADER = "Authorization";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity signUp(SignUpReq signUpReq, @RequestPart(value = "profile", required = false) final MultipartFile profile) {
        try {
            //log.info("controller_signUpReq : " + signUpReq);
            if (profile != null) {
                signUpReq.setProfile(profile);
            }
            return new ResponseEntity<>(userService.signUp(signUpReq), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
