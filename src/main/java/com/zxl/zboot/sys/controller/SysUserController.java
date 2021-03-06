package com.zxl.zboot.sys.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxl.zboot.common.BaseController;
import com.zxl.zboot.common.exception.CustomException;
import com.zxl.zboot.common.utils.R;
import com.zxl.zboot.sys.dto.UserDto;
import com.zxl.zboot.sys.entity.SysUser;
import com.zxl.zboot.sys.service.ISysRoleService;
import com.zxl.zboot.sys.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zxl
 * @since 2019-03-01
 */

@Api(tags = "1.1", value = "用户管理", description = "用户管理")
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class SysUserController extends BaseController {

    private final ISysUserService sysUserService;
    private final ISysRoleService sysRoleService;

    @ApiOperation(value = "注册")
    @PostMapping("/")
    public R register(@RequestBody UserDto userDto) {
        return sysUserService.register(userDto);
    }


    @ApiOperation("分页")
    @GetMapping("/page")
    public R page(@RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "10") int size
            , @RequestParam(required = false) String username
            , @RequestParam(required = false) String deptId) {


        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        if (StrUtil.isNotBlank(deptId)) {
            queryWrapper.like("dept_id", deptId);
        }
        IPage<SysUser> sysUserIPage = sysUserService.page(new Page<>(page, size), queryWrapper);
        for (SysUser sysUser : sysUserIPage.getRecords()) {
            if (sysUser.getRoleId() != null)
                sysUser.setRoleName(sysRoleService.getById(sysUser.getRoleId()).getRoleName());
        }
        return new R<>(sysUserIPage);
    }

    @ApiOperation("测试")
    @GetMapping("/test")
    public R test(@RequestParam int test) {
        if (test < 0) {
            throw new CustomException(400, "这个怕是不能小于0");
        }
        return new R<>(true);
    }


}
