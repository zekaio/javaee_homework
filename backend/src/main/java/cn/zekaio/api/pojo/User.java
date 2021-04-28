package cn.zekaio.api.pojo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@TableName(value = "users", autoResultMap = true)
//@Alias("users")
public class User {
    // 主键
    private Integer userId;

    // uuid
    private String uuid;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 帖子总数
    private String postsNum;

    // 性别
    private String sex;

    // 年级
    private String grade;

    // 专业
    private String major;

    // 个人介绍
    private String description;

    // 头像
    private String avatar;

    // 背景
    private String bg;

    // 粉丝数
    private Integer fansNum;

    // 关注数
    private Integer followNum;

    // 创建时间
    // @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    // 修改时间
//    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;
}
