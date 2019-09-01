package study.spring.deliveryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWishStore {
    private String storeName;
    private String storeProfileUrl;
    private int rating;
    private int storeReviewCnt;
}
