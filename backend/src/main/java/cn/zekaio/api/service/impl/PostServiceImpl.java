package cn.zekaio.api.service.impl;

import cn.zekaio.api.dao.CommentDao;
import cn.zekaio.api.dao.PostDao;
import cn.zekaio.api.dao.UserDao;
import cn.zekaio.api.exception.BusinessException;
import cn.zekaio.api.pojo.Comment;
import cn.zekaio.api.pojo.Post;
import cn.zekaio.api.pojo.User;
import cn.zekaio.api.service.PostService;
import cn.zekaio.api.util.PojoToMapUtil;
import cn.zekaio.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("postService")
public class PostServiceImpl implements PostService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PojoToMapUtil pojoToMapUtil;


    @Override
    public Result getPosts(String uuid, String username, String keyword, Integer lastId, Integer limit) {
        User user = null;
        if (uuid != null) {
            user = userDao.getUserByUUID(uuid);
            if (user == null) {
                throw new BusinessException(404, "用户不存在");
            }
        }

        if (username != null) {
            user = userDao.getUserByUsername(username);
            if (user == null) {
                throw new BusinessException(404, "用户不存在");
            }
        }

        List<Post> posts = postDao.getPosts(user == null ? null : user.getUserId(), keyword, lastId, limit);

        List<Map<String, Object>> ret = new ArrayList<>();

        for (Post post : posts) {
            ret.add(pojoToMapUtil.getPostMap(post));
        }

        return Result.success(ret);
    }

    @Override
    public Result getPost(String postId) {
        Post post = postDao.getPost(postId);
        if (post == null) {
            throw new BusinessException(404, "帖子不存在");
        }

        List<Comment> comments = commentDao.getComments(post.getPostId(), null, 0, null, null, 10);

        Map<String, Object> ret = pojoToMapUtil.getPostMap(post);
        List<Map<String, Object>> commentMaps = new ArrayList<>();
        for (Comment comment : comments) {
            commentMaps.add(pojoToMapUtil.getCommentMap(comment));
        }
        ret.put("comments", commentMaps);

        return Result.success(ret);
    }

    @Override
    public Result savePost(String content, MultipartFile[] imgs, HttpSession session) {
        return null;
    }

    @Override
    public Result updatePost(String content, MultipartFile[] imgs, HttpSession session) {
        return null;
    }

    @Override
    public Result deletePost(String postId, HttpSession session) {
        Post post = postDao.getPost(postId);
        if (post == null) {
            throw new BusinessException(404, "帖子不存在");
        }

        String userId = session.getAttribute("user_id").toString();

        if (!userId.equals(post.getUserId().toString())) {
            throw new BusinessException(403, "没有权限删除帖子");
        }

        User user = userDao.getUserByUserId(userId);
        if (user == null) {
            throw new BusinessException(404, "用户不存在");
        }
        postDao.deletePost(postId);
        userDao.updateUserPostsNum(user.getUserId(), user.getPostsNum() - 1);
        return Result.success("删除成功");
    }
}
