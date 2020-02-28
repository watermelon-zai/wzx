package com.wzx.springbootblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.wzx.springbootblog.domain.*;
import com.wzx.springbootblog.mapper.RoleMapper;
import com.wzx.springbootblog.mapper.UserInfoMapper;
import com.wzx.springbootblog.mapper.UserRoleMapper;
import com.wzx.springbootblog.service.UserService;
import com.wzx.springbootblog.utils.Const;
import com.wzx.springbootblog.utils.PageBean;
import com.wzx.springbootblog.utils.PageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;


    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;


    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    @Override
    public int addUser(UserInfo userInfo) {
       try{
           int i=this.userInfoMapper.insertSelective(userInfo);
           return i;
       }catch(Exception e){
        e.printStackTrace();
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
       }
       return 0;
    }


    /**
     * 无条件的分页查询，即查询出所有的用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<UserInfo> findPageUserList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        UserInfoExample userInfoExample=new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        //查询有效的用户信息 即用户状态为1
        criteria.andUserMarkEqualTo("1");
        List<UserInfo> list = this.userInfoMapper.selectByExample(userInfoExample);
        PageInfo<UserInfo> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }


    /**
     * 有条件的分页查询，根据条件查询用户信息
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<UserInfo> findPageUserListCandition(UserInfo user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        UserInfoExample userInfoExample=new UserInfoExample();
       UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
       //根据输入的用户姓名进行模糊查询
       criteria.andUserNameLike("%"+user.getUserName()+"%");
        //查询有效的用户信息 即用户状态为1
        criteria.andUserMarkEqualTo("1");
        List<UserInfo> list = this.userInfoMapper.selectByExample(userInfoExample);
        PageInfo<UserInfo> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }


    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    @Override
    public UserInfo selectOneUser(Integer id) {
        return this.userInfoMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据用户id 更新用户信息
     * @param userInfo
     * @return
     */
    @Override
    public int updateUserInfo(UserInfo userInfo) {

        int i = this.userInfoMapper.updateByPrimaryKeySelective(userInfo);
            return i;

    }

    /**
     * 删除用户，即将用户的状态修改为 0
     * @param id
     */
    @Override
    public void deleteUser(Integer id) {
        UserInfo userInfo = this.userInfoMapper.selectByPrimaryKey(id);
        userInfo.setUserMark("0");
        this.userInfoMapper.updateByPrimaryKey(userInfo);
    }

    @Override
    public UserInfo findUserByName(String name) {
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andUserNameEqualTo(name);
        List<UserInfo> list = this.userInfoMapper.selectByExample(userInfoExample);
        if (CollectionUtils.isNotEmpty(list)){
            return list.get(0);
        }else {
            return null;
        }

    }


    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {

        /**
         * 根据用户账号查询该用户，并将该用户的所有权限查询出来
         * 返回值 UserDetails 包含
         *   username，
         *   userpassword，
         *   账户是否未过期（true），
         *   账户是否未锁定（true），
         *   凭证是否未过期（true），
         *   是否开启（true），
         *   List<GrantedAuthority>授予的权限，其中包括 角色名，权限名，注意需要将角色名roleName,permissionName事先封装到SimpleGrantedAuthority中，再添加到集合中
         * @param
         * @return
         * @throws UsernameNotFoundException
         */
            //System.out.println(userAccount);
            UserDetails userDetails = null;
            List<String> roleNameList = new ArrayList<>();
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>(0);
            //根据提交的信息来查询用户信息
            UserInfoExample userInfoExample=new UserInfoExample();
            UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
            criteria.andUserAccountEqualTo(userAccount);
            List<UserInfo> userInfoList = this.userInfoMapper.selectByExample(userInfoExample);
            if(CollectionUtils.isNotEmpty(userInfoList)){
                //返回的是一个集合 但是只会有一个
                UserInfo user = userInfoList.get(0);
                //TODO 根据查询到的用户，来查询该用户的角色和权限
                UserRoleExample userRoleExample = new UserRoleExample();
                UserRoleExample.Criteria userRoleExampleCriteria = userRoleExample.createCriteria();
                userRoleExampleCriteria.andUidEqualTo(user.getUserId());

                //查询出用户的所有角色id
                List<UserRoleKey> userRoleKeys = this.userRoleMapper.selectByExample(userRoleExample);

                //根据用户的角色id获取该用户的所有角色名称，并封装成一个roleNameList
                if (CollectionUtils.isNotEmpty(userRoleKeys)){
                    for (UserRoleKey userRoleKey:userRoleKeys) {
                        Integer rid = userRoleKey.getRid();
                        Role role = this.roleMapper.selectByPrimaryKey(rid);
                        String roleName = role.getRoleName();
                        roleNameList.add(roleName);
                    }
                }

                //将角色名封装成所需要的SimpleGrantedAuthority
                /*
                 * 这里将角色名也放到 SimpleGrantedAuthority 中，是为了匹配
                 *<security:intercept-url pattern="/**" access="hasAnyRole('ROLE_ADMIN','ROLE_USER')"/>
                 * 这里事先设定好的角色，如果不放，会出现 forbidden ，不能访问
                 * */
                for (String roleName: roleNameList) {
                    SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleName);
                    grantedAuthorityList.add(grantedAuthority);
                }

                //根据用户id 查询对应的权限名称，并将权限名称封装到 SimpleGrantedAuthority 中后，在添加到 List<GrantedAuthority> 中
                List<Permission> permissionList = this.userInfoMapper.findPermissionByUserId(user.getUserId());
                if (CollectionUtils.isNotEmpty(permissionList)){
                    for (Permission permission:permissionList) {
                        String permisiionName = permission.getPermissionName();
                       /* System.out.println(permisiionName);*/
                        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permisiionName);
                        grantedAuthorityList.add(grantedAuthority);
                    }
                }

                //将数据 添加到 userDetails 中，并返回。
                userDetails = new org.springframework.security.core.userdetails.User(
                        user.getUserName(),           //用户名
                        user.getUserPassword(),           //密码
                        user.getUserMark().equals("1"),  //是否开启，这里指的是用户是否有效
                        true,         //账户是否未过期
                        true,       //凭证是否未过期
                        true,          //账户是否未过期
                        grantedAuthorityList);         //授予的权限


            }

            return userDetails;

        }


    /**
     * 上传图片
     * @param file
     * @return
     */
    @Override
    public String doPutFile(MultipartFile file) {
        try {
            //图片名称
            String fileName = file.getOriginalFilename();
            //当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String format = sdf.format(new Date());

            //拼接url
            String url = Const.USERIMG_URL+format+fileName;
            System.out.println(url);

            //jersey客户端
            Client client = new Client();
            WebResource resource = client.resource(url);

            //将文件转为byte
            byte[] buf = file.getBytes();
            resource.put(String.class,buf);
            return url;

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }


}
