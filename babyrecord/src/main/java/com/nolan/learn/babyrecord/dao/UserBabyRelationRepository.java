package com.nolan.learn.babyrecord.dao;

import com.nolan.learn.babyrecord.model.po.UserBabyRelationPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * description:
 *
 * @author zhun.huang 2019-06-07
 */
@Repository
public interface UserBabyRelationRepository extends JpaRepository<UserBabyRelationPO,Long> {

    List<UserBabyRelationPO> findAllByUserId(String userId);

    UserBabyRelationPO findByBabyIdAndUserId(String babyId,String userId);

    Optional<UserBabyRelationPO> findFirstByUserId(String userId);

}
