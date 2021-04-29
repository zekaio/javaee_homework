package cn.zekaio.api.controller;

import cn.zekaio.api.service.PostService;
import cn.zekaio.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    /**
     * 获取多个帖子
     *
     * @param uuid     uuid
     * @param username 用户名
     * @param keyword  搜索关键字
     * @param lastId   已获取帖子中最后一个帖子的id，默认为0
     * @param limit    要获取的数目， 默认为5
     * @return Result
     */
    @GetMapping("")
    @ResponseBody
    public Result getPosts(@RequestParam(required = false) String uuid, @RequestParam(required = false) String username, @RequestParam(required = false) String keyword, @RequestParam(required = false, name = "last_id") Integer lastId, @RequestParam(defaultValue = "10") Integer limit) {
        return postService.getPosts(uuid, username, keyword, lastId, limit);
    }

    /**
     * 通过id获取帖子
     *
     * @param postId 帖子id
     * @return Result
     */
    @GetMapping("/{postId}")
    @ResponseBody
    public Result getPost(@PathVariable String postId) {
        return postService.getPost(postId);
    }

    /**
     * 发表帖子
     *
     * @param content 帖子内容
     * @param imgs    多张图片
     * @param session HttpSession
     * @return Result
     */
    @PostMapping("")
    @ResponseBody
    public Result savePost(@RequestParam("content") String content, @RequestParam(value = "imgs", required = false) MultipartFile[] imgs, HttpSession session) {
        return postService.savePost(content, imgs, session);
    }

    /**
     * 修改帖子
     *
     * @param postId  帖子id
     * @param content 帖子内容
     * @param imgs    多张图片
     * @param session HttpSession
     * @return Result
     */
    @PutMapping("/{postId}")
    @ResponseBody
    public Result updatePost(@PathVariable String postId, @RequestParam("content") String content, @RequestParam(value = "imgs", required = false) MultipartFile[] imgs, HttpSession session) {
        return postService.updatePost(postId, content, imgs, session);
    }

    /**
     * 删除帖子
     *
     * @param postId 帖子id
     * @return Result
     */
    @DeleteMapping("/{postId}")
    @ResponseBody
    public Result deletePost(@PathVariable String postId, HttpSession session) {
        return postService.deletePost(postId, session);
    }
}
