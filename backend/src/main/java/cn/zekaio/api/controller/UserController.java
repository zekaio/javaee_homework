package cn.zekaio.api.controller;

import cn.zekaio.api.service.UserService;
import cn.zekaio.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param params json: {
     *               username: 用户名,
     *               password: 密码,
     *               check_pwd: 二次验证密码
     *               }
     * @return Result
     */
    @PostMapping("/register")
    @ResponseBody
    public Result createUser(@RequestBody Map<String, String> params) {
        return userService.createUser(params);
    }

//    @PutMapping("/me/password")
//    @ResponseBody
//    public Result updateUserPassword(Map<String, String> params){}

    /**
     * 修改用户信息
     *
     * @param params json: {
     *               username: 用户名,
     *               sex: 性别,
     *               grade: 年级,
     *               major: 专业,
     *               description: 自我介绍
     *               }
     * @param session HttpSession
     * @return Result
     */
    @PutMapping("/me/info")
    @ResponseBody
    public Result updateUserInfo(@RequestBody Map<String, String> params, HttpSession session) {
        return userService.updateUserInfo(params, session);
    }

    /**
     * 上传头像
     * @param file
     * @return
     */
//    @PutMapping("/me/avatar")
//    @ResponseBody
//    public Result updateUserAvatar(@RequestBody Map<String, String> params){}

    /**
     * 上传背景
     */
    //    @PutMapping("/me/bg")
//    @ResponseBody
//    public Result updateUserBackgroundImage(@RequestBody Map<String, String> params){}

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @param uuid     uuid
     * @return Result
     */
    @GetMapping("")
    @ResponseBody
    public Result getUserInfo(@RequestParam(required = false) String username, @RequestParam(required = false) String uuid, HttpSession session) {
        return userService.getUserInfo(username, uuid, session);
    }

    /**
     * 查找用户
     *
     * @param keyword      关键字
     * @param lastUserUUID 上一个用户的uuid
     * @param limit        每次查找的用户数
     * @return Result
     */
    @GetMapping("/search")
    @ResponseBody
    public Result searchUsers(@RequestParam String keyword, @RequestParam(required = false, name = "last_user_uuid") String lastUserUUID, @RequestParam(defaultValue = "10") Integer limit) {
        return userService.searchUsers(keyword, lastUserUUID, limit);
    }

    /**
     * 关注或取关用户
     *
     * @param params  json: {
     *                username: 用户名,
     *                status: bool true关注，false取关
     *                }
     * @param session HttpSession
     * @return Result
     */
    @PutMapping("/me/follow")
    @ResponseBody
    public Result followUser(@RequestBody Map<String, String> params, HttpSession session) {
        return userService.followUser(params, session);
    }

    /**
     * 获取关注的人的列表
     *
     * @param uuid         uuid
     * @param username     用户名
     * @param lastFollowId 上一次请求最后一个用户id
     * @param limit        每次获取数量
     * @return Result
     */
    @GetMapping("/follow")
    @ResponseBody
    public Result getFollowList(@RequestParam(required = false) String uuid, @RequestParam(required = false) String username, @RequestParam(required = false, name = "last_follow_id") Integer lastFollowId, @RequestParam(defaultValue = "10") Integer limit) {
        return userService.getFollowList(uuid, username, lastFollowId, limit);
    }


    /**
     * 获取粉丝列表
     *
     * @param uuid         uuid
     * @param username     用户名
     * @param lastFollowId 上一次请求最后一个用户id
     * @param limit        每次获取数量
     * @return Result
     */
    @GetMapping("/fans")
    @ResponseBody
    public Result getFansList(@RequestParam(required = false) String uuid, @RequestParam(required = false) String username, @RequestParam(required = false, name = "last_follow_id") Integer lastFollowId, @RequestParam(defaultValue = "10") Integer limit) {
        return userService.getFansList(uuid, username, lastFollowId, limit);
    }
}
