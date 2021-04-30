package cn.zekaio.api.service;

import cn.zekaio.api.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {
    Result createUser(Map<String, String> params);

    Result updateUserInfo(Map<String, String> params, HttpSession session);

    Result updateUserAvatar(MultipartFile avatar, HttpSession session);

    Result updateUserBackgroundImage(MultipartFile bg, HttpSession session);

    Result getUserInfo(String username, String uuid, HttpSession session);

    Result searchUsers(String keyword, String lastUserUUID, Integer limit, HttpSession session);

    Result followUser(Map<String, String> params, HttpSession session);

    Result getFollowList(String uuid, String username, Integer lastFollowId, Integer limit);

    Result getFansList(String uuid, String username, Integer lastFollowId, Integer limit);
}
