package com.nolan.learn.springoauth2v2.dao;

import com.nolan.learn.springoauth2v2.model.MyUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * description:
 *
 * @author zhun.huang 2019-03-25
 */
public interface MyUserDao extends JpaRepository<MyUserModel,Long> {

    /**
     * 通过手机号获取用户
     *
     * @param mobile 手机号
     * @return UserModel
     */
    MyUserModel findByMobile(String mobile);

    /**
     * 通过账号获取用户
     *
     * @param username 账号
     * @return UserModel
     */
    MyUserModel findByUsername(String username);
}
