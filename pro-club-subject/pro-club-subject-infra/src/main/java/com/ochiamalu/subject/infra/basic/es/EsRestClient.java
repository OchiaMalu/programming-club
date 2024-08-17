package com.ochiamalu.subject.infra.basic.es;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class EsRestClient {

    public static Map<String, RestHighLevelClient> clients = new HashMap<>();

    @Resource
    private EsConfigProperties esConfigProperties;

    @PostConstruct
    public void init() {
        List<EsClusterConfig> esConfigs = esConfigProperties.getEsConfigs();
        for (EsClusterConfig esConfig : esConfigs) {
            log.info("init es cluster config,name:{},node:{}", esConfig.getName(), esConfig.getNodes());
            RestHighLevelClient highLevelClient = initRestClient(esConfig);
            clients.put(esConfig.getName(), highLevelClient);
        }
    }

    private RestHighLevelClient initRestClient(EsClusterConfig config) {
        String[] ipPostArr = config.getNodes().split(",");
        List<HttpHost> httpHostList = new ArrayList<>(ipPostArr.length);
        for (String ipPort : ipPostArr) {
            String[] ipPortInfo = ipPort.split(":");
            if (ipPortInfo.length == 2) {
                HttpHost httpHost = new HttpHost(ipPortInfo[0], Integer.parseInt(ipPortInfo[1]));
                httpHostList.add(httpHost);
            }
        }
        HttpHost[] httpHosts = new HttpHost[httpHostList.size()];
        httpHostList.toArray(httpHosts);
        RestClientBuilder builder = RestClient.builder(httpHosts);
        return new RestHighLevelClient(builder);
    }
}
