package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IUsersDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUsersDao usersDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 用户登录校验
     * 认证，授权
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    //userDetails里做了什么？不知道
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //需要用户名，加密的密码，和角色名
        UserInfo userInfo = usersDao.UserByUsername(username);
        //这里的user是经过认证（user拿到数据库的信息，和前段传过来的用户密码比对）之后返回的user
        //比对此处怎么实现认证并返回的？
        //{noop}是表示不添加密码编码的密码，不写表示添加密码编码的密码
        User user = new User(userInfo.getUsername(), userInfo.getPassword(),getAuthories(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthories(List<Role> roles){
        ArrayList<SimpleGrantedAuthority> sga = new ArrayList<>();
        for (Role role : roles) {
            //封装权限
            sga.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return sga;
    }

    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> userInfos = usersDao.findAll();
        return userInfos;
    }

    @Override
    public void saveUser(UserInfo user) {
        //给password添加加密编码再储存
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        try {
            usersDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public UserInfo UserById(String id) {
        return usersDao.UserById(id);
    }

    @Override
    public UserInfo findUserByIdAndAllRole(String id) {
        return usersDao.findUserByIdAndAllRole(id);
    }

    @Override
    public void addRoleToUser(String userId, String roleId) {
        usersDao.addRoleToUser(userId,roleId);
    }
}