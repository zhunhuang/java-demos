package com.nolan.learn.babyrecord.model.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.InputStream;
import java.util.Date;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Entity
@Data
@Table(name = "file_info")
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 文件资源ID ，绑定业务来源的ID
     */
    @Column(name = "resource_id")
    private String resourceId;

    @Column(name = "file_original_name")
    private String fileOriginalName;


    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_md5")
    private String fileMd5;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "valid")
    private Boolean valid;

    @Column(name = "gmt_create", insertable = false)
    private Date gmtCreate;

    @Column(name = "gmt_update", insertable = false)
    private Date gmtUpdate;

    @JsonIgnore
    @Transient
    @Column(name = "content", insertable = false)
    private InputStream content;

}
