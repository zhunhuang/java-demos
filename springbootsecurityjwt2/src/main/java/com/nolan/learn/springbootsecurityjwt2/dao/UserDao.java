package com.nolan.learn.springbootsecurityjwt2.dao;


import com.nolan.learn.springbootsecurityjwt2.model.DO.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * description:
 *
 * @author zhun.huang 2019-03-26
 */
public interface UserDao extends JpaRepository<UserDO,Long> {

    /**
     * 根据openId查找用户
     * @param openId
     * @return
     */
    UserDO findByOpenId(String openId);
}
