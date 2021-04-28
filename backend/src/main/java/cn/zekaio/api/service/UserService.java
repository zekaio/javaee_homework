package cn.zekaio.api.service;

import cn.zekaio.api.vo.Result;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {
    Result createUser(Map<String, String> params);

//    Result updateUserPassword(Map<String, String> params);

    Result updateUserInfo(Map<String, String> params, HttpSession session);

    //    Result updateUserAvatar();
//    Result updateUserBackgroundImage();
    Result getUserInfo(String username, String uuid, HttpSession session);

    Result searchUsers(String keyword, String lastUserUUID, Integer limit);

    Result followUser(Map<String, String> params, HttpSession session);

    Result getFollowList(String uuid, String username, Integer lastFollowId, Integer limit);

    Result getFansList(String uuid, String username, Integer lastFollowId, Integer limit);
}
