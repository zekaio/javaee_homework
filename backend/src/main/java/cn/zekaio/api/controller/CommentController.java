//package cn.zekaio.api.controller;
//
//import cn.zekaio.api.vo.Result;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/comments")
//public class CommentController {
//    /**
//     * 获取多个评论
//     *
//     * @param uuid     uuid
//     * @param username 用户名
//     * @param keyword  搜索关键字
//     * @param lastId   已获取评论中最后一个评论的id，默认为0
//     * @param limit    要获取的数目， 默认为5
//     * @return Result
//     */
//    @GetMapping("")
//    @ResponseBody
//    public Result getComments(@RequestParam(required = false) String uuid, @RequestParam(required = false) String username, @RequestParam(required = false) String keyword, @RequestParam(required = false, name = "last_id") String lastId, @RequestParam(defaultValue = "10") String limit) {
//    }
//
//    /**
//     * 通过id获取评论
//     *
//     * @param commentId 评论id
//     * @return Result
//     */
//    @GetMapping("/{commentId}")
//    @ResponseBody
//    public Result getComment(@PathVariable String commentId) {
//    }
//
//    /**
//     * 发表评论
//     *
//     * @param params  json: {
//     *                  content: 评论内容
//     *                }
//     * @param request HttpServletRequest
//     * @return Result
//     */
//    @PostMapping("")
//    @ResponseBody
//    public Result saveComment(@RequestBody Map<String, String> params, HttpServletRequest request) {
//    }
//
//    /**
//     * 修改评论
//     *
//     * @param params  json: {
//     *                  content: 评论内容
//     *                }
//     * @param request HttpServletRequest
//     * @return Result
//     */
//    @PutMapping("/{commentId}")
//    @ResponseBody
//    public Result updateComment(@RequestBody Map<String, String> params, HttpServletRequest request) {
//    }
//
//    /**
//     * 删除评论
//     *
//     * @param commentId 评论id
//     * @return Result
//     */
//    @DeleteMapping("/{commentId}")
//    @ResponseBody
//    public Result deleteComment(@PathVariable String commentId) {
//    }
//}
