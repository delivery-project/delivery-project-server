package study.spring.deliveryproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import study.spring.deliveryproject.dto.User;
import study.spring.deliveryproject.mapper.UserMapper;
import study.spring.deliveryproject.model.DefaultRes;
import study.spring.deliveryproject.model.SignUpReq;
import study.spring.deliveryproject.utils.ResponseMessage;
import study.spring.deliveryproject.utils.StatusCode;


@Slf4j
@Service
public class UserService {
    private final UserMapper userMapper;
    private final S3FileUploadService s3FileUploadService;

    public UserService(UserMapper userMapper, S3FileUploadService s3FileUploadService) {
        this.userMapper = userMapper;
        this.s3FileUploadService = s3FileUploadService;
    }

    //회원가입
    @Transactional
    public DefaultRes signUp(SignUpReq signUpReq) {
        try{
            //log.error("service_signUpReq : " + signUpReq);
            if(signUpReq.getProfile() != null){
                signUpReq.setProfileUrl(s3FileUploadService.upload(signUpReq.getProfile()));
            }
            userMapper.signUp(signUpReq);
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.SIGNUP_SUCCESS);
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

}


