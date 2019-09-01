package study.spring.deliveryproject.utils;

public class ResponseMessage {

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";

    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String SIGNUP_SUCCESS = "회원가입 성공";

    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    public static final String READ_USER_WISHSTORE  = "회원 찜한가게 조회 성공";


    public static final String READ_BOARD = "게시글 조회 성공";
    public static final String NOT_FOUND_BOARD = "게시글을 찾을 수 없습니다.";
    public static final String CREATED_BOARD = "게시글 작성 성공";
    public static final String UPDATE_BOARD = "게시글 수정 성공";
    public static final String DELETE_BOARD = "게시글 삭제 성공";

    public static final String READ_COMMENT = "댓글 조회 성공";
    public static final String NOT_FOUND_COMMENT = "댓글 조회 실패";
    public static final String CREATED_COMMENT_SUCCESS  = "댓글 작성 성공";
    public static final String CREATED_COMMENT_FAIL  = "댓글 작성 실패";
    public static final String UPDATE_COMMENT_SUCCESS  = "댓글 수정 성공";
    public static final String UPDATE_COMMENT_FAIL  = "댓글 수정 실패";
    public static final String DELETE_COMMENT_SUCCESS  = "댓글 탈퇴 성공";
    public static final String DELETE_COMMENT_FAIL  = "댓글 탈퇴 실패";


    public static final String AUTHORIZED = "인증 성공";
    public static final String UNAUTHORIZED = "인증 실패";
    public static final String FORBIDDEN = "인가 실패";
}