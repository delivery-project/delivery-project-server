package study.spring.deliveryproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.spring.deliveryproject.dto.User;
import study.spring.deliveryproject.mapper.AuthMapper;
import study.spring.deliveryproject.model.DefaultRes;
import study.spring.deliveryproject.model.LoginReq;
import study.spring.deliveryproject.utils.ResponseMessage;
import study.spring.deliveryproject.utils.StatusCode;
import study.spring.deliveryproject.utils.auth.JwtUtils;

@Slf4j
@Service
public class AuthService {
    private final AuthMapper authMapper;
    private final JwtService jwtService;

    public AuthService(AuthMapper authMapper, JwtService jwtService) {
        this.authMapper = authMapper;
        this.jwtService = jwtService;
    }

    /*public DefaultRes<JwtService.TokenRes> login(LoginReq loginReq) {*/
//    public DefaultRes<JwtUtils.TokenRes> login(LoginReq loginReq) {
//        final User user = authMapper.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
//        if(user != null){
//            final JwtUtils.TokenRes tokenRes = new JwtUtils.TokenRes(jwtService.create(user.getIdx()));
//            return DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, tokenRes);
//        }
//        return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.LOGIN_FAIL);
//    }

    public DefaultRes<JwtUtils.TokenRes> login(final LoginReq loginReq) {
        final User user = authMapper.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
        if (user != null) {
            final JwtUtils.TokenRes tokenDto = new JwtUtils.TokenRes(JwtUtils.create(user.getEmail()));
            return DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, tokenDto);
        }
        return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.LOGIN_FAIL);
    }

}
