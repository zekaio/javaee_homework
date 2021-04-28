package cn.zekaio.api.pojo;

//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.ArrayTypeHandler;

import java.util.Date;
import java.util.List;

@Data
@TableName(value = "posts", autoResultMap = true)
// @Alias("posts")
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
//    @TableField(typeHandler = ArrayTypeHandler.class)
    private List<String> imgsName;

    // 创建时间
//    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    // 修改时间
//    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    // 删除时间
    private Date deletedAt;
}
