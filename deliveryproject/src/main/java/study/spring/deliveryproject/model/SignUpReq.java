package study.spring.deliveryproject.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SignUpReq{
    private String name;
    private String email;
    private String password;
    private MultipartFile profile;
    private String profileUrl;
    private String nickname;
    private String phoneNum;


    public boolean checkElement() {
        if(!isthereEmail() || !istherePassword() || !isthereName() || !istherePhone() ) {
            return false;
        }else {
            return true;
        }
    }

    public boolean isthereEmail() {
        if( email == null ) {
            return false;
        }else {
            return true;
        }
    }


    public boolean istherePassword() {
        if(password == null ) {
            return false;
        }else {
            return true;
        }
    }

    public boolean isthereName() {
        if(name == null) {
            return false;
        }else {
            return true;
        }
    }

    public boolean istherePhone() {
        if(phoneNum == null ) {
            return false;
        }else {
            return true;
        }
    }

}
