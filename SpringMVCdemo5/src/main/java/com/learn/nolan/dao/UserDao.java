package com.learn.nolan.dao;

import com.learn.nolan.model.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * description:
 *
 * @author zhun.huang 2018-12-17
 */
@Repository
public interface UserDao {

    UserDO queryById(@Param("id") long id);
}
