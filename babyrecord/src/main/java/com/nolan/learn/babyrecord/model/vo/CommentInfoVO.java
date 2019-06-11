package com.nolan.learn.babyrecord.model.vo;

import com.nolan.learn.babyrecord.model.po.CommentInfoPO;
import lombok.Builder;
import lombok.Data;

/**
 * description:
 *
 * @author zhun.huang 2019-06-07
 */
@Data
@Builder
public class CommentInfoVO {

    private Long id;

    private String commenterId;

    private String commentName;

    private String repliedId;

    private String repliedName;

    private String content;

    private Long gmtCreate;

    private Long gmtUpdate;

    public static CommentInfoVO poToVO(CommentInfoPO commentInfoPO) {
        return CommentInfoVO.builder()
                .id(commentInfoPO.getId())
                .commenterId(commentInfoPO.getCommenterId())
                .commentName(commentInfoPO.getCommentName())
                .repliedId(commentInfoPO.getRepliedId())
                .repliedName(commentInfoPO.getRepliedName())
                .content(commentInfoPO.getContent())
                .gmtCreate(commentInfoPO.getGmtCreate().getTime())
                .gmtUpdate(commentInfoPO.getGmtUpdate().getTime())
                .build();
    }
}
