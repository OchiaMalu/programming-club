package com.ochiamalu.oss.controller;

import com.ochiamalu.oss.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/upload")
    public String uploadFile(MultipartFile uploadFile, String bucket, String objectName) {
        return fileService.uploadFile(uploadFile, bucket, objectName);
    }

    @GetMapping("/getUrl")
    public String getUrl(String bucket, String objectName) {
        return fileService.getUrl(bucket, objectName);
    }

    @GetMapping("/getAllBucket")
    public List<String> getAllBucket() {
        return fileService.getAllBucket();
    }
}
