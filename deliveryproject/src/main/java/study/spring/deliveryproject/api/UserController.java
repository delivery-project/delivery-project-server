package study.spring.deliveryproject.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.spring.deliveryproject.dto.User;
import study.spring.deliveryproject.model.DefaultRes;
import study.spring.deliveryproject.model.SignUpReq;
import study.spring.deliveryproject.service.UserService;
import study.spring.deliveryproject.utils.auth.JwtUtils;


import javax.servlet.http.HttpServletRequest;

import static study.spring.deliveryproject.model.DefaultRes.FAIL_DEFAULT_RES;
import static study.spring.deliveryproject.model.DefaultRes.UNAUTHORIZED_RES;

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


    //회원 정보 조회
    @GetMapping("/mypage")
    public ResponseEntity getMypage(final HttpServletRequest httpServletRequest) {
        try {
            final String tokenValue = JwtUtils.decode(httpServletRequest.getHeader(HEADER)).getEmail();
            DefaultRes<User> defaultRes = userService.findByUserEmail(tokenValue);
            log.info("controller_tokenValue : " + tokenValue); //null....;;
            /*if (tokenValue.compareTo(tokenValue) == 0)
                defaultRes.getData().setAuth(true);*/
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    //회원 정보 수정 => profileUrl, nickname, phoneNum
    @PutMapping("/mypage")
    public ResponseEntity updateUser(@RequestHeader("Authorization") final String jwt,
                                     SignUpReq signUpReq,
                                     @RequestPart(value = "profile", required=false) final MultipartFile profile) {
        try {
            if(profile != null){
                signUpReq.setProfile(profile);
            }
            if(signUpReq.getNickname() != null){
                signUpReq.setNickname(signUpReq.getNickname());
            }if(signUpReq.getPhoneNum() != null){
                signUpReq.setPhoneNum(signUpReq.getPhoneNum());
            }
            log.info("controller_signUpReq : " + signUpReq);
            final String token_value = JwtUtils.decode(jwt).getEmail();
            if(token_value != null){
                return new ResponseEntity<>(userService.updateUser(token_value, signUpReq), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(UNAUTHORIZED_RES, HttpStatus.OK);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //회원 탈퇴
    @DeleteMapping("/{idx}")
    public ResponseEntity deleteByUserIdx(
            @PathVariable(value = "idx") final int idx) {
        try {
            return new ResponseEntity<>(userService.deleteByUserIdx(idx), HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //회원이 찜한 스토어
    @GetMapping("/wishstore/{idx}")
    public ResponseEntity findByUserWishStore(@PathVariable(value = "idx") final int idx) {
        try {
            return new ResponseEntity(userService.findByUserWishStore(idx), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
