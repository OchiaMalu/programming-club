package com.ochiamalu.oss.controller;

import com.ochiamalu.oss.service.FileService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文件控制器
 *
 * @author OchiaMalu
 * @date 2024/08/11
 */
@RestController
public class FileController {

    @Resource
    private FileService fileService;

}
