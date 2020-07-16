package com.chen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chen
 */
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    private String uploadDir;
    private String imageMagic;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getImageMagic() {
        return imageMagic;
    }

    public void setImageMagic(String imageMagic) {
        this.imageMagic = imageMagic;
    }
}
