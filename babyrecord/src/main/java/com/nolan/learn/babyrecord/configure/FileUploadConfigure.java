package com.nolan.learn.babyrecord.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Data
@Configuration
@Component
@ConfigurationProperties(prefix = "upload")
public class FileUploadConfigure {

    /**
     * 获取存放位置
     */
    private Map<String, String> location;

    /**
     * 单个文件大小
     */
    private String maxFileSize;

    /**
     * 单次上传总文件大小
     */
    private String maxRequestSize;

    public String getBasePath() {
        String location = "";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            location = this.getLocation().get("windows");
        } else {
            location = this.getLocation().get("linux");
        }
        return location;
    }

}
