package cn.zekaio.api.dao;

import cn.zekaio.api.pojo.Follow;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowDao {
    Follow getFollowById(@Param("user_id") Integer userId, @Param("followed_user_id") Integer followedUserId);

    void updateFollowStatus(@Param("follow_id") Integer followId, @Param("status") Integer status);

    void createFollow(@Param("user_id") Integer userId, @Param("followed_user_id") Integer followedUserId);

    List<Follow> getFollowList(@Param("user_id") Integer userId, @Param("limit") Integer limit, @Param("last_follow_id") Integer lastFollowId);

    List<Follow> getFansList(@Param("followed_user_id") Integer followedUserId, @Param("limit") Integer limit, @Param("last_follow_id") Integer lastFollowId);
}
