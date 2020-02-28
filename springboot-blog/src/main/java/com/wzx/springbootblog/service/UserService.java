package com.wzx.springbootblog.service;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.UserInfo;
import com.wzx.springbootblog.utils.PageBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * 添加用户信息
     * @param userInfo
     * @return
     */
    public int addUser(UserInfo userInfo);


    /**
     * 无条件的分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<UserInfo> findPageUserList(Integer pageNum,Integer pageSize);


    /**
     * 有条件的分页查询
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<UserInfo> findPageUserListCandition(UserInfo user,Integer pageNum,Integer pageSize);


    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public UserInfo selectOneUser(Integer id);


    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    public int updateUserInfo(UserInfo userInfo);


    /**
     * 删除用户信息
     * @param id
     */
    void deleteUser(Integer id);

    UserInfo findUserByName(String name);

    /**
     * 头像上传
     * @param file
     * @return
     */
    public String doPutFile(MultipartFile file);
}
