package com.ochiamalu.oss.adapter;

import com.ochiamalu.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class AliyunStorageAdapter implements StorageAdapter {
    @Override
    public void createBucket(String bucketName) {

    }

    @Override
    public void uploadFile(MultipartFile multipartFile, String bucketName, String objectName) {

    }

    @Override
    public List<String> getAllBuckets() {
        return Collections.emptyList();
    }

    @Override
    public List<FileInfo> getAllFiles(String bucketName) {
        return Collections.emptyList();
    }

    @Override
    public InputStream downloadFile(String bucketName, String objectName) {
        return null;
    }

    @Override
    public void deleteFile(String bucketName, String objectName) {

    }

    @Override
    public void deleteBucket(String bucketName) {

    }

    @Override
    public String getUrl(String bucket, String objectName) {
        return "aliyun";
    }
}
