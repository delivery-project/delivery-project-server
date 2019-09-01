package study.spring.deliveryproject.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import study.spring.deliveryproject.dto.User;
import study.spring.deliveryproject.dto.UserWishStore;
import study.spring.deliveryproject.model.DefaultRes;
import study.spring.deliveryproject.model.SignUpReq;

import java.util.List;


@Mapper
public interface UserMapper {
    //회원가입
    @Insert("INSERT INTO user(name, email, profile_url, nickname, phone_number, password) VALUES (#{signUpReq.name}, #{signUpReq.email}, #{signUpReq.profileUrl}, #{signUpReq.nickname}, #{signUpReq.phoneNum}, #{signUpReq.password})")
    int signUp(@Param("signUpReq") final SignUpReq signUpReq);

    //회원 정보 userIdx로 조회
    @Select("SELECT * FROM user WHERE idx = #{idx}")
    User findByUserIdx(@Param("idx") final int idx);

    //회원 정보 email로 조회
    @Select("SELECT * FROM user WHERE email = #{email}")
    User findByUserEmail(@Param("email") final String email);

    @Update("UPDATE user SET profile_url = #{user.profileUrl}, nickname = #{user.nickname}, phone_number = #{user.phoneNum} WHERE email = #{user.email}")
    void updateUser(@Param("email") final String email, @Param("user") final User user);

    //회원 탈퇴
    @Delete("DELETE FROM user WHERE idx = #{idx}")
    void deleteByUserIdx(@Param("idx") final int idx);

    //회원이 찜한 스토어
    /*store_like table -> user_idx, store_idx
    store table -> name, profile_url, rating,
    review table -> 리뷰개수 reviewCnt (store_idx로 cnt)*/
    @Select("SELECT s.name, s.profile_url, s.rating, COUNT(s.idx) AS reviewCnt FROM store_like l INNER JOIN store s ON l.store_idx = s.idx INNER JOIN review r ON r.idx = s.idx WHERE l.user_idx = #{user_idx}")
    UserWishStore findAllUserWishStore(@Param("user_idx") final int user_idx);
}
