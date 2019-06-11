package com.nolan.learn.babyrecord.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-06-07
 */
@Data
@Builder
public class UserInfo {

    private String userId;

    private String nickName;

    private BabyInfoVO babyInfoVO;
}
