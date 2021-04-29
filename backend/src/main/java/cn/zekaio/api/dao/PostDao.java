package cn.zekaio.api.dao;


import cn.zekaio.api.pojo.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao {
    List<Post> getPosts(@Param("user_id") Integer userId, @Param("keyword") String keyword, @Param("last_id") Integer lastId, @Param("limit") Integer limit);

    Post getPost(@Param("post_id") String postId);

    void savePost(@Param("user_id") String userId, @Param("content") String content, @Param("imgs_name") String imgsName);

    void updatePost(@Param("post_id") String postId, @Param("content") String content, @Param("imgs_name") String imgsName);

    void deletePost(@Param("post_id") String postId);
}
