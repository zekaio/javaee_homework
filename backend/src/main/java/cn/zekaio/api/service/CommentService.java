package cn.zekaio.api.service;

import cn.zekaio.api.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface CommentService {
    Result getComments(String parentId, String type, Integer lastId, Integer limit);

    Result getComment(String commentId);

    Result saveComment(String content, MultipartFile img, String parentId, Integer type, HttpSession session);

    Result updateComment(String commentId, String content, MultipartFile img, HttpSession session);

    Result deleteComment(String commentId, HttpSession session);
}
