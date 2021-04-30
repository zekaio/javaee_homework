package cn.zekaio.api.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("posts")
public class Post {
    // 主键
    private Integer postId;

    // 发帖人id
    private Integer userId;

    // 内容
    private String content;

    // 评论数量
    private Integer commentsNum;

    // 图片文件名
    private String imgsName;

    // 创建时间
    private Date createdAt;

    // 修改时间
    private Date updatedAt;

    // 删除时间
    private Date deletedAt;
}
