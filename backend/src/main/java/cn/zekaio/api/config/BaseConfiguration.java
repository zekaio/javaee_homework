package cn.zekaio.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// 将自定义注释注入实体类中
@Data
@Configuration
@ConfigurationProperties(prefix = "base")
public class BaseConfiguration {
    private String dirPrefix;

    private String avatarDirName;

    private String bgDirName;

    private String postImageDirName;

    private String commentImageDirName;

    public String getAvatarDirName() {
        return String.format("%s/%s/", dirPrefix, avatarDirName);
    }

    public String getBgDirName() {
        return String.format("%s/%s/", dirPrefix, bgDirName);
    }

    public String getPostImageDirName() {
        return String.format("%s/%s/", dirPrefix, postImageDirName);
    }

    public String getCommentImageDirName() {
        return String.format("%s/%s/", dirPrefix, commentImageDirName);
    }
}
