package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll();

    void savePermission(Permission permission);

    Permission findByPermissionid(String id);

    void deleteByPid(String id);

}
