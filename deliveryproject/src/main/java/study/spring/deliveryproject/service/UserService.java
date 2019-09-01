package study.spring.deliveryproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import study.spring.deliveryproject.dto.User;
import study.spring.deliveryproject.dto.UserWishStore;
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

    //회원가입  ok
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



    //회원 정보 userIdx로 조회  ok
    public DefaultRes findByUserIdx (final int idx){
        final User user = userMapper.findByUserIdx(idx);
        log.error("service_user : " + user);
        if(user == null){
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER);
        }else{
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, user);
        }
    }

    //mypage조회  ok
    //회원 정보 email 조회
    public DefaultRes<User> findByUserEmail (final String email){
        final User user = userMapper.findByUserEmail(email);
        if(user == null){
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER);
        }else{
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, user);
        }
    }


    //회원 정보 수정 => profileUrl, nickname, phoneNum
    @Transactional
    public DefaultRes updateUser(final String token_value, final SignUpReq signUpReq) {
        User tempUser = findByUserEmail(token_value).getData();
        if(tempUser == null){
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER);
        }
        try{
            if(signUpReq.getProfile() != null){
                tempUser.setProfileUrl(s3FileUploadService.upload(signUpReq.getProfile()));
            }
            if(signUpReq.getNickname() != null){
                tempUser.setNickname(signUpReq.getNickname());
            }
            if(signUpReq.getPhoneNum() != null){
                tempUser.setPhoneNum(signUpReq.getPhoneNum());
            }
            log.info("service_signUpReq : " + signUpReq); //email, nickname 안찍힘
            log.info("service_tempUser : " + tempUser);
            userMapper.updateUser(token_value, tempUser);
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.UPDATE_USER);
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    //회원 탈퇴
    @Transactional
    public DefaultRes deleteByUserIdx(int idx) {
        final User user = userMapper.findByUserIdx(idx);
        if(user == null){
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER);
        }
        try{
            userMapper.deleteByUserIdx(idx);
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.DELETE_USER);
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }


    //회원이 찜한 가게
    public DefaultRes findByUserWishStore (final int idx){
        //final User user = userMapper.findByUserIdx(idx);
        final UserWishStore userWishStore = userMapper.findAllUserWishStore(idx);
        log.error("service_findByUserWishStore_user : " + userWishStore);
        if(userWishStore == null){
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USERWISHSTORE);
        }else{
            return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USERWISHSTORE, userWishStore);
        }
    }

}


