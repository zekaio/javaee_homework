package cn.zekaio.api.dao;

import cn.zekaio.api.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User getUserByUsername(@Param("username") String username);

    User getUserByUserId(@Param("user_id") String userId);

    User getUserByUUID(@Param("uuid") String uuid);

    void createUser(@Param("username") String username, @Param("uuid") String uuid, @Param("password") String password);

    void updateUserInfo(@Param("user_id") String userId, @Param("username") String username, @Param("sex") String sex, @Param("grade") String grade, @Param("major") String major, @Param("description") String description);


//    void
}
