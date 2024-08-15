package com.ochiamalu.oss.adapter;

import com.ochiamalu.oss.entity.FileInfo;
import com.ochiamalu.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * minio存储适配器
 *
 * @author OchiaMalu
 * @date 2024/08/11
 */
public class MinioStorageAdapter implements StorageAdapter {

    @Resource
    private MinioUtil minioUtil;

    /**
     * minioUrl
     */
    @Value("${minio.url}")
    private String url;

    @Override
    @SneakyThrows
    public void createBucket(String bucketName) {
        minioUtil.createBucket(bucketName);
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile multipartFile, String bucketName, String objectName) {
        minioUtil.createBucket(bucketName);
        if (objectName != null) {
            minioUtil.uploadFile(
                    multipartFile.getInputStream(),
                    bucketName,
                    objectName + "/" + multipartFile.getName());
        } else {
            minioUtil.uploadFile(
                    multipartFile.getInputStream(),
                    bucketName,
                    multipartFile.getName());
        }
    }

    @Override
    @SneakyThrows
    public List<String> getAllBuckets() {
        return minioUtil.getAllBuckets();
    }

    @Override
    @SneakyThrows
    public List<FileInfo> getAllFiles(String bucketName) {
        return minioUtil.getAllFiles(bucketName);
    }

    @Override
    @SneakyThrows
    public InputStream downloadFile(String bucketName, String objectName) {
        return minioUtil.downloadFile(bucketName, objectName);
    }

    @Override
    @SneakyThrows
    public void deleteFile(String bucketName, String objectName) {
        minioUtil.deleteFile(bucketName, objectName);
    }

    @Override
    @SneakyThrows
    public void deleteBucket(String bucketName) {
        minioUtil.deleteBucket(bucketName);
    }

    @Override
    public String getUrl(String bucket, String objectName) {
        return url + "/" + bucket + "/" + objectName;
    }
}
