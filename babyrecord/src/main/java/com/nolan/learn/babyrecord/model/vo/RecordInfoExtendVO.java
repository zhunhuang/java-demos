package com.nolan.learn.babyrecord.model.vo;

import com.nolan.learn.babyrecord.model.po.RecordInfoPO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.nolan.learn.babyrecord.utils.CommonUtils.COMMA_SPLITTER;

/**
 * description:
 *
 * @author zhun.huang 2019-06-07
 */
@Setter
@Getter
public class RecordInfoExtendVO extends RecordInfoVO {

    private List<CommentInfoVO> commentInfoList;


    @Builder
    public RecordInfoExtendVO(Long id, String babyId, String userId, List<String> imgUrlList, String content, Long gmtCreate, Long gmtUpdate, List<CommentInfoVO> commentInfoList) {
        super(id, babyId, userId, imgUrlList, content, gmtCreate, gmtUpdate);
        this.commentInfoList = commentInfoList;
    }

    public static RecordInfoExtendVO poToVO(RecordInfoPO po, List<CommentInfoVO> commentInfoList) {
        return RecordInfoExtendVO.builder()
                .id(po.getId())
                .babyId(po.getBabyId())
                .userId(po.getUserId())
                .content(po.getContent())
                .imgUrlList(COMMA_SPLITTER.splitToList(po.getImgUrls()))
                .gmtCreate(po.getGmtCreate().getTime())
                .gmtUpdate(po.getGmtUpdate().getTime())
                .commentInfoList(commentInfoList)
                .build();
    }

}
