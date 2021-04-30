package cn.zekaio.api.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("follow")
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
