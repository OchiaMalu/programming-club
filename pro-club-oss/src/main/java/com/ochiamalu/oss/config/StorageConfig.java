package com.ochiamalu.oss.config;

import com.ochiamalu.oss.adapter.AliyunStorageAdapter;
import com.ochiamalu.oss.adapter.MinioStorageAdapter;
import com.ochiamalu.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 存储配置
 *
 * @author OchiaMalu
 * @date 2024/08/11
 */
@Configuration
@RefreshScope
public class StorageConfig {

    @Value("${storage.service.type}")
    private String storageType;

    @Bean
    @RefreshScope
    public StorageAdapter storageAdapter() {
        if ("minio".equals(storageType)) {
            return new MinioStorageAdapter();
        }
        if ("aliyun".equals(storageType)) {
            return new AliyunStorageAdapter();
        }
        return null;
    }
}
