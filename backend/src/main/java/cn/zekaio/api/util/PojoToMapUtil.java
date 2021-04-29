package cn.zekaio.api.util;

import cn.zekaio.api.dao.CommentDao;
import cn.zekaio.api.dao.PostDao;
import cn.zekaio.api.dao.UserDao;
import cn.zekaio.api.pojo.Comment;
import cn.zekaio.api.pojo.Follow;
import cn.zekaio.api.pojo.Post;
import cn.zekaio.api.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PojoToMapUtil {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private CommentDao commentDao;

    public Map<String, Object> getUserMap(User user) {
        Map<String, Object> map = new HashMap<>();

        map.put("user_id", user.getUserId());
        map.put("username", user.getUsername());
        map.put("uuid", user.getUuid());
        map.put("posts_num", user.getPostsNum());
        map.put("sex", user.getSex());
        map.put("grade", user.getGrade());
        map.put("major", user.getMajor());
        map.put("description", user.getDescription());
        map.put("avatar", user.getAvatar());
        map.put("bg", user.getBg());
        map.put("fans_num", user.getFansNum());
        map.put("follow_num", user.getFollowNum());

        return map;
    }

    public Map<String, Object> getPostMap(Post post) {
        Map<String, Object> map = new HashMap<>();

        map.put("post_id", post.getPostId());
        map.put("content", post.getContent());
        map.put("comments_num", post.getCommentsNum());
        map.put("created_at", post.getCreatedAt());
        map.put("updated_at", post.getUpdatedAt());
        map.put("imgs_name", post.getImgsName().split(","));

        User author = userDao.getUserByUserId(post.getUserId().toString());
        map.putAll(getUserMap(author));

        return map;
    }

    public Map<String, Object> getCommentMap(Comment comment) {
        Map<String, Object> map = new HashMap<>();

        map.put("comment_id", comment.getCommentId());
        map.put("parent_id", comment.getParentId());
        map.put("root_id", comment.getRootId());
        map.put("post_id", comment.getPostId());
        map.put("type", comment.getType());
        map.put("content", comment.getContent());
        map.put("comments_num", comment.getCommentsNum());
        map.put("img_name", comment.getImgName());
        map.put("created_at", comment.getCreatedAt());
        map.put("updated_at", comment.getUpdatedAt());

        User author = userDao.getUserByUserId(comment.getUserId().toString());
        map.putAll(getUserMap(author));

        Integer parentAuthorId;

        if (comment.getType().equals(0)) {
            Post parent = postDao.getPost(comment.getParentId().toString());
            parentAuthorId = parent.getUserId();
        } else {
            Comment parent = commentDao.getComment(comment.getParentId().toString());
            parentAuthorId = parent.getUserId();
        }

        User parentUser = userDao.getUserByUserId(parentAuthorId.toString());
        map.put("parent_user_info", getUserMap(parentUser));

        return map;
    }

    public Map<String, Object> getFollowInfo(Follow follow) {
        Map<String, Object> map = new HashMap<>();

        map.put("follow_id", follow.getFollowId());
        map.put("status", follow.getStatus().equals(1));

        User followedUser = userDao.getUserByUserId(follow.getFollowedUserId().toString());
        map.put("followed_user_id", followedUser.getUserId());
        map.put("followed_user_username", followedUser.getUsername());
        map.put("followed_user_avatar", followedUser.getAvatar());
        map.put("followed_user_description", followedUser.getDescription());

        User fans = userDao.getUserByUserId(follow.getUserId().toString());
        map.put("user_id", fans.getUserId());
        map.put("user_username", fans.getUsername());
        map.put("user_avatar", fans.getAvatar());
        map.put("user_description", fans.getDescription());

        return map;
    }


}
