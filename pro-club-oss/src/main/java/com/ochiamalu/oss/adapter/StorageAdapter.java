package com.ochiamalu.oss.adapter;

import com.ochiamalu.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 存储适配器
 *
 * @author OchiaMalu
 * @date 2024/08/11
 */
public interface StorageAdapter {

    void createBucket(String bucketName);

    void uploadFile(MultipartFile multipartFile, String bucketName, String objectName);

    List<String> getAllBuckets();

    List<FileInfo> getAllFiles(String bucketName);

    InputStream downloadFile(String bucketName, String objectName);

    void deleteFile(String bucketName, String objectName);

    void deleteBucket(String bucketName);

    String getUrl(String bucket, String objectName);
}
