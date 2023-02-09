package com.itheima.sh.stock.mapper;

import com.itheima.sh.stock.pojo.entity.SysPermission;

/**
* @author olouca
* @description 针对表【sys_permission(权限表（菜单）)】的数据库操作Mapper
* @createDate 2023-02-07 21:03:54
* @Entity theima.sh.stock.pojo.entity.SysPermission
*/
public interface SysPermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

}
