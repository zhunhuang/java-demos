//package com.nolan.learn.springsecurityjwt1.dao;
//
//import com.nolan.learn.springsecurityjwt1.model.DO.UserConnectionDO;
//import org.springframework.data.jpa.repository.JpaRepository;
//
///**
// * description:
// *
// * @author zhun.huang 2019-03-26
// */
//public interface UserConnectionDao extends JpaRepository<UserConnectionDO,Long> {
//
//    /**
//     * 根据openId查找
//     * @param openId
//     * @return
//     */
//    UserConnectionDO findByOpenId(String openId);
//
//    /**
//     * 根据openId和sessionKey查找
//     * @param openId
//     * @param sessionKey
//     * @return
//     */
//    UserConnectionDO findByOpenIdAndSessionKey(String openId, String sessionKey);
//}
