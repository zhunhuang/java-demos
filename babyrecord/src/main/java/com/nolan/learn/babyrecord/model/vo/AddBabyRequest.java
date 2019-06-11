package com.nolan.learn.babyrecord.model.vo;

import com.nolan.learn.babyrecord.model.po.BabyInfoPO;
import lombok.Data;

/**
 * description:
 *
 * @author zhun.huang 2019-06-08
 */
@Data
public class AddBabyRequest {

    private String userId;

    private String babyName;

    private String iconUrl;

    private String backgroundUrl;

    public static BabyInfoPO requestToPO(AddBabyRequest request) {
        BabyInfoPO babyInfoPO = new BabyInfoPO();
        babyInfoPO.setBabyId("baby"+ request.getUserId());
        babyInfoPO.setBabyName(request.getBabyName());
        babyInfoPO.setIconUrl(request.getIconUrl());
        babyInfoPO.setBackgroundUrl(request.getBackgroundUrl());
        return babyInfoPO;
    }

}
