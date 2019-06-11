package com.nolan.learn.babyrecord.dao;

import com.nolan.learn.babyrecord.model.po.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

    FileInfo findByFileName(String fileName);

    FileInfo findByFileUrlAndValid(String fileUrl, boolean valid);

    List<FileInfo> findByResourceId(String resourceId);

}
