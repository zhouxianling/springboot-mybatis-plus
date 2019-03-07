package com.zxl.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxl.demo.common.BaseController;
import com.zxl.demo.dto.DeptDto;
import com.zxl.demo.entity.SysDept;
import com.zxl.demo.service.ISysDeptService;
import com.zxl.demo.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author zxl
 * @since 2019-03-01
 */
@Api(tags = "部门", value = "部门管理", description = "部门管理")
@RestController
@RequestMapping("api/dept")
@AllArgsConstructor
public class SysDeptController extends BaseController {

    private final ISysDeptService sysDeptService;

    @ApiOperation("新增")
    @PostMapping("/")
    public R save(@RequestBody DeptDto deptDto) {
        return sysDeptService.saveDept(deptDto);
    }

    @ApiOperation("更新")
    @PutMapping("/")
    public R update(@RequestBody DeptDto deptDto) {
        return sysDeptService.updateDept(deptDto);
    }

    @ApiOperation("详情")
    @GetMapping("/{id}")
    public R detail(@PathVariable Integer id) {
        return new R<>(sysDeptService.getById(id));
    }

    @ApiOperation("逻辑删除")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return new R<>(sysDeptService.logicDeleteDeptById(id));
    }


    @ApiOperation("分页接口")
    @GetMapping("/page")
    public R page(@RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "10") int size
            , @RequestParam(required = false) String name) {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        if (name != null) {
            queryWrapper.like("name", "%" + name + "%");
        }
        return new R<>(sysDeptService.page(new Page<>(page, size), queryWrapper));
    }

    @ApiOperation("部门树")
    @GetMapping("/tree")
    public R tree() {
        return new R<>(sysDeptService.selectTree());
    }

}
