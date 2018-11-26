package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> permissionsByRoleid(String id);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void savePermission(Permission permission);

    @Select("select * from permission where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "roles",many = @Many(select = "com.itheima.ssm.dao.IRoleDao.rolesByPermissionId"))
    })
    Permission findByPermissionid(String id);

    @Delete("delete from permission where id = #{id}")
    void deleteByPid(String id);

    /**
     *一个角色没有的权限
     * @param id
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> outofPersByRoleid(String id);
}
