package cn.zekaio.api.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "follow", autoResultMap = true)
public class Follow {
    // 主键
    private Integer followId;

    // 用户id
    private Integer userId;

    // 被关注的人的id
    private Integer followedUserId;

    // 1：正在关注，0：取消关注
    private Integer status;

    // 创建时间
    private Date createdAt;

    // 修改时间
    private Date updatedAt;
}
