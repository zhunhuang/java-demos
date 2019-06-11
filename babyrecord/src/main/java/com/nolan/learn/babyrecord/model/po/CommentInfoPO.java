package com.nolan.learn.babyrecord.model.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Data
@Entity
@Table(name = "comment_info")
public class CommentInfoPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "commenter_id")
    private String commenterId;

    @Column(name = "commenter_name")
    private String commentName;

    @Column(name = "replied_id")
    private String repliedId;

    @Column(name = "content")
    private String content;

    @Column(name = "replied_name")
    private String repliedName;

    @Column(name = "gmt_create", insertable = false)
    private Date gmtCreate;

    @Column(name = "gmt_update", insertable = false)
    private Date gmtUpdate;

}
