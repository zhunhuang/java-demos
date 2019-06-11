package com.nolan.learn.babyrecord.dao;

import com.nolan.learn.babyrecord.model.po.BabyInfoPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Repository
public interface BabyInfoRepository extends JpaRepository<BabyInfoPO, Long> {

    BabyInfoPO findByBabyId(String babyId);

    List<BabyInfoPO> findAllByBabyId(Iterable<String> babyIds);


}
