package cn.zekaio.api.service.impl;

import cn.zekaio.api.config.BaseConfiguration;
import cn.zekaio.api.dao.CommentDao;
import cn.zekaio.api.dao.PostDao;
import cn.zekaio.api.dao.UserDao;
import cn.zekaio.api.exception.BusinessException;
import cn.zekaio.api.pojo.Comment;
import cn.zekaio.api.pojo.Post;
import cn.zekaio.api.pojo.User;
import cn.zekaio.api.service.CommentService;
import cn.zekaio.api.util.FileUtil;
import cn.zekaio.api.util.PojoToMapUtil;
import cn.zekaio.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PojoToMapUtil pojoToMapUtil;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private BaseConfiguration baseConfiguration;

    @Override
    public Result getComments(String parentId, String type, Integer lastId, Integer limit) {
        Integer commentsNum;
        if (type.equals("0")) {
            Post post = postDao.getPost(parentId);
            if (post == null) {
                throw new BusinessException(404, "帖子不存在");
            }
            commentsNum = post.getCommentsNum();
        } else {
            Comment comment = commentDao.getComment(parentId);
            if (comment == null) {
                throw new BusinessException(404, "评论不存在");
            }
            commentsNum = comment.getCommentsNum();
        }

        List<Comment> comments = commentDao.getComments(null, Integer.valueOf(parentId), Integer.valueOf(type), null, lastId, limit);

        Map<String, Object> ret = new HashMap<>();

        List<Map<String, Object>> commentList = new ArrayList<>();

        for (Comment comment : comments) {
            Map<String, Object> map = new HashMap<>();
            commentList.add(pojoToMapUtil.getCommentMap(comment));
        }

        ret.put("comments", commentList);
        ret.put("comments_num", commentsNum);

        return Result.success(ret);
    }

    @Override
    public Result getComment(String commentId) {
        Comment comment = commentDao.getComment(commentId);

        if (comment == null) {
            throw new BusinessException(404, "评论不存在");
        }

        List<Comment> comments = commentDao.getComments(null, comment.getCommentId(), 1, null, null, 10);

        List<Map<String, Object>> commentMaps = new ArrayList<>();

        for (Comment c : comments) {
            commentMaps.add(pojoToMapUtil.getCommentMap(c));
        }

        Map<String, Object> ret = new HashMap<>();
        ret.put("comments", commentMaps);
        ret.putAll(pojoToMapUtil.getCommentMap(comment));

        return Result.success(ret);
    }

    @Override
    public Result saveComment(String content, MultipartFile img, String parentId, Integer type, HttpSession session) {
        String userId = session.getAttribute("user_id").toString();

        User user = userDao.getUserByUserId(userId);
        if (user == null) {
            throw new BusinessException(404, "用户不存在");
        }
        Integer postId, rootId;
        if (type.equals(1)) {
            Comment comment = commentDao.getComment(parentId);
            if (comment == null) {
                throw new BusinessException(404, "评论不存在");
            }
            commentDao.updateCommentCommentsNum(comment.getCommentId(), comment.getCommentsNum() + 1);
            postId = comment.getPostId();
            rootId = comment.getRootId();
        } else {
            Post post = postDao.getPost(parentId);
            if (post == null) {
                throw new BusinessException(404, "帖子不存在");
            }
            postDao.updatePostCommentsNum(post.getPostId(), post.getCommentsNum() + 1);
            postId = post.getPostId();
            rootId = post.getPostId();
        }

        String path = baseConfiguration.getCommentImageDirName();
        String fileName = fileUtil.saveMultipartFile(img, path);

        commentDao.saveComment(userId, content, fileName, parentId, rootId.toString(), postId.toString(), type.toString());
        return Result.success("发表成功");
    }

    @Override
    public Result updateComment(String commentId, String content, MultipartFile img, HttpSession session) {
        String userId = session.getAttribute("user_id").toString();
        Comment comment = commentDao.getComment(commentId);
        if (comment == null) {
            throw new BusinessException(404, "评论不存在");
        }
        User user = userDao.getUserByUserId(userId);
        if (user == null) {
            throw new BusinessException(404, "用户不存在");
        }

        if (!comment.getUserId().equals(user.getUserId())) {
            throw new BusinessException(403, "没有权限修改评论");
        }

        String path = baseConfiguration.getCommentImageDirName();
        String fileName = fileUtil.saveMultipartFile(img, path);

        commentDao.updateComment(commentId, content, fileName);

        return Result.success("更新成功");
    }

    @Override
    public Result deleteComment(String commentId, HttpSession session) {
        Comment comment = commentDao.getComment(commentId);
        if (comment == null) {
            throw new BusinessException(404, "评论不存在");
        }

        commentDao.deleteComment(commentId);
        return Result.success("删除成功");
    }
}
