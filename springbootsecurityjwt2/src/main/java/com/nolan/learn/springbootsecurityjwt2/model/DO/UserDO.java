package com.nolan.learn.springbootsecurityjwt2.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * description:
 *
 * @author zhun.huang 2019-03-26
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserDO {

    @Id
    @Column(name = "uid")
    private Long uid;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "roles")
    private String roles;
}
