package com.example.nolan.springtransaction2.dao.user;

import com.example.nolan.springtransaction2.model.user.UserDO;
import org.springframework.data.repository.CrudRepository;

/**
 * description:
 *
 * @author zhunhuang, 2020/4/18
 */
public interface UserRepository extends CrudRepository<UserDO, Long> {

    UserDO findByName(String name);
}
