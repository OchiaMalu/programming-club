package com.ochiamalu.oss.controller;

import com.ochiamalu.oss.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文件控制器
 *
 * @author OchiaMalu
 * @date 2024/08/11
 */
@RestController
@RequestMapping("/oss")
public class FileController {

    @Resource
    private FileService fileService;

    @GetMapping("/getAllName")
    public String getAllName(){
        return fileService.getUrl("","");
    }
}
