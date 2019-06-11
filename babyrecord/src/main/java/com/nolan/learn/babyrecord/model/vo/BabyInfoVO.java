package com.nolan.learn.babyrecord.model.vo;

import com.nolan.learn.babyrecord.model.po.BabyInfoPO;
import lombok.Builder;
import lombok.Data;

/**
 * description:
 *
 * @author zhun.huang 2019-06-07
 */
@Data
@Builder
public class BabyInfoVO {

    private String babyId;

    private String babyName;

    private String iconUrl;

    private String backgroundUrl;

    private Integer privilegeLevel;

    public static BabyInfoVO poToVO(BabyInfoPO po, Integer privilegeLevel) {
        return BabyInfoVO.builder()
                .babyId(po.getBabyId())
                .babyName(po.getBabyName())
                .iconUrl(po.getIconUrl())
                .backgroundUrl(po.getBackgroundUrl())
                .privilegeLevel(privilegeLevel)
                .build();
    }
}
