package com.nolan.learn.babyrecord.dao;

import com.nolan.learn.babyrecord.model.po.RecordInfoPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Repository
public interface RecordInfoRepository extends JpaRepository<RecordInfoPO,Long> {

    Page<RecordInfoPO> findByBabyId(String babyId, Pageable pageable);
}
