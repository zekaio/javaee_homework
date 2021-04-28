//package cn.zekaio.api.controller;
//
//import cn.zekaio.api.vo.Result;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/posts")
//public class PostController {
//
//    /**
//     * 获取多个帖子
//     *
//     * @param uuid     uuid
//     * @param username 用户名
//     * @param keyword  搜索关键字
//     * @param lastId   已获取帖子中最后一个帖子的id，默认为0
//     * @param limit    要获取的数目， 默认为5
//     * @return Result
//     */
//    @GetMapping("")
//    @ResponseBody
//    public Result getPosts(@RequestParam(required = false) String uuid, @RequestParam(required = false) String username, @RequestParam(required = false) String keyword, @RequestParam(required = false, name = "last_id") String lastId, @RequestParam(defaultValue = "10") String limit) {
//    }
//
//    /**
//     * 通过id获取帖子
//     *
//     * @param postId 帖子id
//     * @return Result
//     */
//    @GetMapping("/{postId}")
//    @ResponseBody
//    public Result getPost(@PathVariable String postId) {
//    }
//
//    /**
//     * 发表帖子
//     *
//     * @param params  json: {
//     *                  content: 帖子内容
//     *                }
//     * @param request HttpServletRequest
//     * @return Result
//     */
//    @PostMapping("")
//    @ResponseBody
//    public Result savePost(@RequestBody Map<String, String> params, HttpServletRequest request) {
//    }
//
//    /**
//     * 修改帖子
//     *
//     * @param params  json: {
//     *                  content: 帖子内容
//     *                }
//     * @param request HttpServletRequest
//     * @return Result
//     */
//    @PutMapping("/{postId}")
//    @ResponseBody
//    public Result updatePost(@RequestBody Map<String, String> params, HttpServletRequest request) {
//    }
//
//    /**
//     * 删除帖子
//     *
//     * @param postId 帖子id
//     * @return Result
//     */
//    @DeleteMapping("/{postId}")
//    @ResponseBody
//    public Result deletePost(@PathVariable String postId) {
//    }
//}
