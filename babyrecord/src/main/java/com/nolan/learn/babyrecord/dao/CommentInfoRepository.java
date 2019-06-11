package com.nolan.learn.babyrecord.dao;

import com.nolan.learn.babyrecord.model.po.CommentInfoPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
public interface CommentInfoRepository extends JpaRepository<CommentInfoPO, Long> {

    List<CommentInfoPO> findByRecordId(Long recordId);
}
