package com.example.nolan.springtransaction2.service;

import com.example.nolan.springtransaction2.dao.user.UserRepository;
import com.example.nolan.springtransaction2.model.user.UserDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * description:
 *
 * @author zhunhuang, 2020/4/18
 */
@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class, transactionManager = "txManager")
//    @Transactional(rollbackFor = Exception.class)
    public void saveUsers(List<UserDO> userDOS) {
        for (UserDO userDO : userDOS) {
            if (userDO == null || StringUtils.isEmpty(userDO.getName())
                    || StringUtils.isEmpty(userDO.getPassword())) {
                throw new IllegalArgumentException("参数错误");
            }
            userRepository.save(userDO);
            System.out.println("落地数据库：" + userDO.getName());
        }
    }


    public UserDO getByName(String name) {
        return userRepository.findByName(name);
    }

    public void saveUsersSelfCall(List<UserDO> userDOS){
        saveUsers(userDOS);
    }
}
