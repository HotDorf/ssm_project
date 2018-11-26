package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    /**
     * 多对多
     * 根据指定userId查询多个roles
     * @param id
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "permissions",many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.permissionsByRoleid"))
    })
    List<Role> roleByUserid(String id);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void saveRole(Role role);

    /**
     * 多对多
     * permissions
     * @param id
     * @return
     */
    @Select("select * from role where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "permissions",many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.permissionsByRoleid"))
    })
    Role RoleById(String id);

    /**
     * 删除role
     * @param id
     */
    @Delete("delete from role where id = #{id}")
    void deleteByRoleid(String id);

    /**
     * 多对多
     * @param id:permissionId
     * @return
     */
    @Select("select * from role where id in (select roleId from role_permission where permissionId = #{id})")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "users",many = @Many(select = "com.itheima.ssm.dao.IUsersDao.UsersByRoleid"))
    })
    List<Role> rolesByPermissionId(String id);

    /**
     *一个用户没有的角色
     * @param id
     * @return
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    List<Role> outofRoleIdByUserId(String id);


    /**
     * 一个角色，permissions属性里的权限是此角色没有的
     * @param id
     * @return
     */
    @Select("select * from role where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "permissions",
                    many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.outofPersByRoleid"))
    })
    Role findRoleByIdAndAllPermission(String id);


    /**
     * 增加角色权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPerToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
