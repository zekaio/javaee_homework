package cn.zekaio.api.controller;

import cn.zekaio.api.service.CommentService;
import cn.zekaio.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 获取多个评论
     *
     * @param parentId 父节点id
     * @param type     评论类型
     * @param lastId   已获取评论中最后一个评论的id，默认为0
     * @param limit    要获取的数目， 默认为10
     * @return Result
     */
    @GetMapping("")
    @ResponseBody
    public Result getComments(@RequestParam(name = "parent_id") String parentId, @RequestParam String type, @RequestParam(required = false, name = "last_id") Integer lastId, @RequestParam(defaultValue = "10") Integer limit) {
        return commentService.getComments(parentId, type, lastId, limit);
    }

    /**
     * 通过id获取评论
     *
     * @param commentId 评论id
     * @return Result
     */
    @GetMapping("/{commentId}")
    @ResponseBody
    public Result getComment(@PathVariable String commentId) {
        return commentService.getComment(commentId);
    }

    /**
     * 发表评论
     *
     * @param content  评论内容
     * @param img      图片
     * @param session  HttpSession
     * @param parentId 父节点id
     * @param type     评论类型
     * @return Result
     */
    @PostMapping("")
    @ResponseBody
    public Result saveComment(@RequestParam("content") String content, @RequestParam(value = "img", required = false) MultipartFile img, @RequestParam(value = "parent_id") String parentId, @RequestParam(value = "type") Integer type, HttpSession session) {
        return commentService.saveComment(content, img, parentId, type, session);
    }

    /**
     * 修改评论
     *
     * @param commentId 评论id
     * @param content   评论内容
     * @param img       图片
     * @param session   HttpSession
     * @return Result
     */
    @PutMapping("/{commentId}")
    @ResponseBody
    public Result updateComment(@PathVariable String commentId, @RequestParam("content") String content, @RequestParam(value = "img", required = false) MultipartFile img, HttpSession session) {
        return commentService.updateComment(commentId, content, img, session);
    }

    /**
     * 删除评论
     *
     * @param commentId 评论id
     * @param session   HttpSession
     * @return Result
     */
    @DeleteMapping("/{commentId}")
    @ResponseBody
    public Result deleteComment(@PathVariable String commentId, HttpSession session) {
        return commentService.deleteComment(commentId, session);
    }
}
