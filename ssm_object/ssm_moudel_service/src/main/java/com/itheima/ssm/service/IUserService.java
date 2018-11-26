package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    /**
     * 用户查询
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 保存用户
     */
    void saveUser(UserInfo user);

    /**
     * 通过id找User
     * @return
     */
    UserInfo UserById(String id);

    /**
     * user没有的角色
     * @param id
     * @return
     */
    UserInfo findUserByIdAndAllRole(String id);

    void addRoleToUser(String userId, String roleId);
}
