package com.nolan.learn.springsecurityjwt1.model.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * description:
 *
 * @author zhun.huang 2019-03-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_connection")
public class UserConnectionDO {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "session_key")
    private String sessionKey;

    @Column(name = "session_expire_time")
    private Date sessionExpireTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;
}
