package com.nolan.learn.babyrecord.model.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Entity
@Data
@Table(name = "record_info")
public class RecordInfoPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "baby_id")
    private String babyId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "img_urls")
    private String imgUrls;

    @Column(name = "content")
    private String content;

    @Column(name = "gmt_create", insertable = false)
    private Date gmtCreate;

    @Column(name = "gmt_update", insertable = false)
    private Date gmtUpdate;
}
