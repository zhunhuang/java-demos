package com.example.nolan.springtransaction2.service;

import com.example.nolan.springtransaction2.StartAppTests;
import com.example.nolan.springtransaction2.model.user.UserDO;
import org.assertj.core.util.Lists;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class UserServiceTest extends StartAppTests {

    @Resource
    private UserService userService;

    @Test
    public void saveUsers_transaction_success() {
        UserDO user1 = getUser(1);
        UserDO user2 = getUser(2);
        userService.saveUsers(Lists.newArrayList(user1, user2));
        UserDO user1FromDB = userService.getByName(user1.getName());

        assertEquals(user1.getName(), user1FromDB.getName());
        assertEquals(user1.getPassword(), user1FromDB.getPassword());
        assertNotNull(user1FromDB.getId());
    }

    @Test
    public void saveUsers_transaction_rollBack_success() {
        UserDO user3 = getUser(3);
        UserDO user4 = new UserDO();
        try {
            userService.saveUsers(Lists.newArrayList(user3, user4));
        } catch (Exception e) {

        }
        UserDO user3FromDB = userService.getByName(user3.getName());

        assertNull(user3FromDB);
    }

    @Test
    public void saveUsers_transaction_rollBack_fail_on_selfCall() {
        UserDO user5 = getUser(5);
        UserDO user6 = new UserDO();
        try {
            userService.saveUsersSelfCall(Lists.newArrayList(user5, user6));
        } catch (Exception e) {

        }
        UserDO user5FromDB = userService.getByName(user5.getName());
        System.out.println(user5FromDB);
        assertNotNull(user5FromDB);
    }

    private UserDO getUser(int i) {
        UserDO userDO = new UserDO();
        userDO.setName("aa" + i);
        userDO.setPassword("bb" + i);
        return userDO;
    }
}