package cn.zekaio.api.dao;


import cn.zekaio.api.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    List<Comment> getComments(@Param("parent_id") Integer parentId, @Param("root_id") Integer rootId, @Param("type") Integer type, @Param("keyword") String keyword, @Param("last_id") Integer lastId, @Param("limit") Integer limit);

    Comment getComment(@Param("comment_id") String commentId);

    void saveComment(@Param("user_id") String userId, @Param("content") String content, @Param("img_name") String imgName, @Param("parent_id") String parentId, @Param("root_id") String rootId, @Param("post_id") String postId, @Param("type") String type);

    void updateComment(@Param("comment_id") String commentId, @Param("content") String content, @Param("img_name") String imgName);

    void deleteComment(@Param("comment_id") String commentId);

    void updateCommentCommentsNum(@Param("comment_id") Integer commentId, @Param("comments_num") Integer commentsNum);
}
