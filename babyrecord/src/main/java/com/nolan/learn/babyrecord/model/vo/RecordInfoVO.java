package com.nolan.learn.babyrecord.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-06-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordInfoVO {

    private Long id;

    private String babyId;

    private String userId;

    private List<String> imgUrlList;

    private String content;

    private Long gmtCreate;

    private Long gmtUpdate;
}
