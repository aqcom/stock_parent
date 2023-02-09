package com.itheima.sh.stock.mapper;

import com.itheima.sh.stock.pojo.entity.SysUserRole;

/**
* @author olouca
* @description 针对表【sys_user_role(用户角色表)】的数据库操作Mapper
* @createDate 2023-02-07 21:03:54
* @Entity theima.sh.stock.pojo.entity.SysUserRole
*/
public interface SysUserRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

}
