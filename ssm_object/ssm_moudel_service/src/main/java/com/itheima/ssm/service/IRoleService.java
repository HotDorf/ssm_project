package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    /**
     * @return
     */
    List<Role> findAll();

    void saveRole(Role role);

    Role roleById(String id);

    void deleteByRoleid(String id);

    Role findRoleByIdAndAllPermission(String id);

    void addPerToRole(String roleId, String permissionId);
}
