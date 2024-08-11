package com.ochiamalu.oss.entity;

import lombok.Data;

/**
 * 文件信息
 *
 * @author OchiaMalu
 * @date 2024/08/11
 */
@Data
public class FileInfo {

    private String fileName;

    private Boolean directoryFlag;

    private String etag;

}
