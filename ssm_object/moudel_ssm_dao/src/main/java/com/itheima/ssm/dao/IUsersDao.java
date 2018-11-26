package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUsersDao {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id",property = "roles",many = @Many(select = "com.itheima.ssm.dao.IRoleDao.roleByUserid"))
    })
    UserInfo UserByUsername(String username);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll();

    /**
     * 添加用户
     * @param userInfo
     * @throws Exception
     */
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    /**
     * @param id
     * @return
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id",property = "roles",many = @Many(select = "com.itheima.ssm.dao.IRoleDao.roleByUserid"))
    })
    UserInfo UserById(String id);

    /**
     * 多对多
     * @param id：roleId
     * @return
     */
    @Select("select * from users where id in (select userId from users_role where roleId = #{id})")
    List<UserInfo> UsersByRoleid(String id);

    /**
     *多对多
     *一个用户没有的角色
     * @param id
     * @return
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "roles",many = @Many(select = "com.itheima.ssm.dao.IRoleDao.outofRoleIdByUserId"))
    })
    UserInfo findUserByIdAndAllRole(String id);

    /**
     * 添加用户角色
     * @param userId
     * @param roleId
     * 此处的@Param是Map，“userId”是key，String userId是value。因为，如果传递的参数多个，ognl表达式无法一一对应。
     * 如果只有一个的时候就不需要Map集合。
     * 封装成实体类，ognl表达式也可以自己找到实体类中的get方法。
     */
    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}

