package study.spring.deliveryproject.utils.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.auth0.jwt.JWT.require;

@Slf4j
@Service
public class JwtUtils {

    private static final String ISSUER = "aon";
    private static final String SECRET = "adsf@#weFDS23TGFDTHGQ#$TFDASDFERF$#&*%(^%aSFDG#%";

    //토큰 생성
    public static String create(final String email)
    {
        try{
            //토큰 생성 빌더 객체 생성
            JWTCreator.Builder b = JWT.create();
            //토큰 생성자 명시
            b.withIssuer(ISSUER);
            //토큰 payload작성, key-value형식, 객체도 가능
            b.withClaim("email", email);
            //토큰 해싱해서 반환
            return b.sign(Algorithm.HMAC256(SECRET));
        }catch (JWTCreationException JwtCreationException){
            log.info(JwtCreationException.getMessage());
        }
        return null;
    }

    //토큰 해독
    public static Token decode(final String token) {
        try {
            final JWTVerifier jwtVerifier = require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return new Token(decodedJWT.getClaim("email").asString());
        } catch (JWTVerificationException jve) {
            log.error(jve.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new Token();
    }

    public static class Token {
        //토큰에 담길 정보 필드
        //초기값을 -1로 설정함으로써 로그인 실패시 -1반환
        private String email = null;
        public Token() {
        }
        public Token(final String email) {
            this.email = email;
        }
        public String getEmail() {
            return email;
        }
    }


    //반환될 토큰Res
    public static class TokenRes {
        //실제 토큰
        private String token;
        public TokenRes() {
        }
        public TokenRes(final String token) {
            this.token = token;
        }
        public String getToken() {
            return token;
        }
        public void setToken(String token) {
            this.token = token;
        }
    }
}