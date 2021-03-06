package com.zxl.zboot.sys.dto;

import com.zxl.zboot.sys.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends SysUser {

    /**
     * token 信息
     */
    private String token;

    /**
     * 菜单id
     */
    private List<MenuTree> menus;

}
