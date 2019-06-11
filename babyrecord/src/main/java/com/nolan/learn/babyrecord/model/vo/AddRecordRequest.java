package com.nolan.learn.babyrecord.model.vo;

import com.nolan.learn.babyrecord.model.po.RecordInfoPO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.nolan.learn.babyrecord.utils.CommonUtils.COMMA_JOINER;

/**
 * description:
 *
 * @author zhun.huang 2019-06-08
 */
@Data
public class AddRecordRequest {

    @NotNull
    private String userId;

    @NotNull
    private String babyId;

    private List<String> imgUrlList;

    private String content;

    public static RecordInfoPO requestToPO(AddRecordRequest request) {
        RecordInfoPO recordInfoPO = new RecordInfoPO();
        recordInfoPO.setBabyId(request.getBabyId());
        recordInfoPO.setUserId(request.getUserId());
        recordInfoPO.setContent(request.getContent());
        recordInfoPO.setImgUrls(COMMA_JOINER.join(request.getImgUrlList()));
        return recordInfoPO;
    }
}
