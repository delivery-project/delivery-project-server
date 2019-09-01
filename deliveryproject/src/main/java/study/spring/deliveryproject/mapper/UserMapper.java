package study.spring.deliveryproject.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import study.spring.deliveryproject.dto.User;
import study.spring.deliveryproject.model.DefaultRes;
import study.spring.deliveryproject.model.SignUpReq;


@Mapper
public interface UserMapper {
    //회원가입
    @Insert("INSERT INTO user(name, email, profile_url, nickname, phone_number, password) VALUES (#{signUpReq.name}, #{signUpReq.email}, #{signUpReq.profileUrl}, #{signUpReq.nickname}, #{signUpReq.phoneNum}, #{signUpReq.password})")
    int signUp(@Param("signUpReq") final SignUpReq signUpReq);

    //회원 정보 userIdx로 조회
    @Select("SELECT * FROM user WHERE idx = #{idx}")
    User findByUserIdx(@Param("idx") final int idx);
}
