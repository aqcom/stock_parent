package com.itheima.sh.stock.mapper;

import com.itheima.sh.stock.pojo.entity.SysRole;

/**
* @author olouca
* @description 针对表【sys_role(角色表)】的数据库操作Mapper
* @createDate 2023-02-07 21:03:54
* @Entity theima.sh.stock.pojo.entity.SysRole
*/
public interface SysRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

}
