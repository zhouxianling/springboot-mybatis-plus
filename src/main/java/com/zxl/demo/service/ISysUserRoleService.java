package com.zxl.demo.service;

import com.zxl.demo.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author zxl
 * @since 2019-03-07
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户ID删除该用户的角色关系
     *
     * @param userId 用户ID
     * @return
     */
    Boolean deleteByUserId(Integer userId);
}
