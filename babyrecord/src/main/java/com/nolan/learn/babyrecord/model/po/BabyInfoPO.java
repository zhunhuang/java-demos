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
@Table(name = "baby_info")
public class BabyInfoPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "baby_id")
    private String babyId;

    @Column(name = "baby_name")
    private String babyName;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "background_url")
    private String backgroundUrl;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_update")
    private Date gmtUpdate;

}
