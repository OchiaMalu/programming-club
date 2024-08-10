package com.ochiamalu.subject.common.entity;


import lombok.Data;

/**
 * 分页信息
 *
 * @author OchiaMalu
 * @date 2024/08/10
 */
@Data
public class PageInfo {

    private Integer pageNo = 1;
    private Integer pageSize = 20;

    public Integer getPageNo() {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        return pageNo;
    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize < 1) {
            return 20;
        }
        return pageSize;
    }
}
