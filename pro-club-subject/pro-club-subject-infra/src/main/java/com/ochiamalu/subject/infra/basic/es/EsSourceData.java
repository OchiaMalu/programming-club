package com.ochiamalu.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * es源数据
 *
 * @author OchiaMalu
 * @date 2024/08/17
 */
@Data
public class EsSourceData implements Serializable {

    private String docId;

    private Map<String,Object> data;
}
