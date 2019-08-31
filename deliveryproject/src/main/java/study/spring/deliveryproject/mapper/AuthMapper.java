package study.spring.deliveryproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import study.spring.deliveryproject.dto.User;

@Mapper
public interface AuthMapper {
    @Select("SELECT * FROM user WHERE email = #{email} AND password = #{password}")
    User findByEmailAndPassword(@Param("email") final String email, @Param("password") final String password);
}
