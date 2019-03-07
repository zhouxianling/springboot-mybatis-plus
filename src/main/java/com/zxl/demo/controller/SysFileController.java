package com.zxl.demo.controller;


import com.zxl.demo.common.BaseController;
import com.zxl.demo.service.ISysFileService;
import com.zxl.demo.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxl
 * @since 2019-03-05
 */
@Api(tags = "文件", value = "文件管理", description = "文件管理")
@RestController
@RequestMapping("api/file")
@AllArgsConstructor
public class SysFileController extends BaseController {


    private final ISysFileService fileService;


    @ApiOperation("单文件上传")
    @PostMapping("/upload")
    @ResponseBody
    public R upload(@RequestParam("file") MultipartFile file) {
        return new R<>(fileService.upload(file));
    }

    @ApiOperation("多文件上传")
    @PostMapping("/uploads")
    @ResponseBody
    public R uploads(@RequestParam("files") MultipartFile[] files) {
        return new R<>(fileService.uploads(files));
    }


}
