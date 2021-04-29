package cn.zekaio.api.service;

import cn.zekaio.api.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface PostService {
    Result getPosts(String uuid, String username, String keyword, Integer lastId, Integer limit);

    Result getPost(String postId);

    Result savePost(String content, MultipartFile[] imgs, HttpSession session);

    Result updatePost(String postId, String content, MultipartFile[] imgs, HttpSession session);

    Result deletePost(String postId, HttpSession session);
}
