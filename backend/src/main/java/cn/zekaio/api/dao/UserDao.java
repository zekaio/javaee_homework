package cn.zekaio.api.dao;

import cn.zekaio.api.pojo.Follow;
import cn.zekaio.api.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    User getUserByUsername(@Param("username") String username);

    User getUserByUserId(@Param("user_id") String userId);

    User getUserByUUID(@Param("uuid") String uuid);

    void createUser(@Param("username") String username, @Param("uuid") String uuid, @Param("password") String password);

    void updateUserInfo(@Param("user_id") String userId, @Param("username") String username, @Param("sex") String sex, @Param("grade") String grade, @Param("major") String major, @Param("description") String description);

    void updateUserFollowNum(@Param("user_id") Integer userId, @Param("follow_num") Integer followNum);

    void updateUserFansNum(@Param("user_id") Integer userId, @Param("fans_num") Integer fansNum);

    void updateUserPostsNum(@Param("user_id") Integer userId, @Param("posts_num") Integer postsNum);

}
