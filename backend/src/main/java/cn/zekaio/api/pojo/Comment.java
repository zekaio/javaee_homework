package cn.zekaio.api.pojo;

//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@TableName(value = "comments", autoResultMap = true)
// @Alias("comments")
public class Comment {
    // 主键
    private Integer commentId;

    // 评论用户id
    private Integer userId;

    // 被评论的帖子或评论的id
    private Integer parentId;

    // 对帖子的评论是帖子id，否则是根评论id
    private Integer rootId;

    // 帖子的id
    private Integer postId;

    // 是什么的评论，0是帖子，1是评论
    private Integer type;

    // 内容
    private String content;

    // 评论数量
    private Integer commentsNum;

    // 评论图片
    private String imgName;

    // 创建时间
//    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    // 修改时间
//    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    // 删除时间
    private Date deletedAt;
}
