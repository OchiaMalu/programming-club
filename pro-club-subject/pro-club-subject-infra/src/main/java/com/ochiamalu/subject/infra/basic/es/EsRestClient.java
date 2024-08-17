package com.ochiamalu.subject.infra.basic.es;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class EsRestClient {

    public static Map<String, RestHighLevelClient> clients = new HashMap<>();

    @Resource
    private EsConfigProperties esConfigProperties;

    private static final RequestOptions COMMON_OPTIONS = RequestOptions.DEFAULT;

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

    private static RestHighLevelClient getClient(String clusterName) {
        return clients.get(clusterName);
    }

    public static Boolean insertDoc(EsIndexInfo indexInfo,
                                    EsSourceData sourceData) {
        try {
            IndexRequest indexRequest = new IndexRequest(indexInfo.getIndexName());
            indexRequest.source(sourceData.getData());
            indexRequest.id(sourceData.getDocId());
            getClient(indexInfo.getClusterName()).index(indexRequest, COMMON_OPTIONS);
            return true;
        } catch (Exception e) {
            log.info("insertDoc.exception:{}", e.getMessage());
        }
        return false;
    }


    public static boolean updateDoc(EsIndexInfo esIndexInfo, EsSourceData esSourceData) {
        try {
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.index(esIndexInfo.getIndexName());
            updateRequest.id(esSourceData.getDocId());
            updateRequest.doc(esSourceData.getData());
            getClient(esIndexInfo.getClusterName()).update(updateRequest, COMMON_OPTIONS);
            return true;
        } catch (Exception e) {
            log.error("updateDoc.exception:{}", e.getMessage(), e);
        }
        return false;
    }

    public static boolean batchUpdateDoc(EsIndexInfo esIndexInfo,
                                         List<EsSourceData> esSourceDataList) {
        try {
            boolean flag = false;
            BulkRequest bulkRequest = new BulkRequest();
            for (EsSourceData esSourceData : esSourceDataList) {
                String docId = esSourceData.getDocId();
                if (StringUtils.isNotBlank(docId)) {
                    UpdateRequest updateRequest = new UpdateRequest();
                    updateRequest.index(esIndexInfo.getIndexName());
                    updateRequest.id(esSourceData.getDocId());
                    updateRequest.doc(esSourceData.getData());
                    bulkRequest.add(updateRequest);
                    flag = true;
                }
            }
            if (flag) {
                BulkResponse bulk = getClient(esIndexInfo.getClusterName()).bulk(bulkRequest, COMMON_OPTIONS);
                return !bulk.hasFailures();
            }
            return true;
        } catch (Exception e) {
            log.error("batchUpdateDoc.exception:{}", e.getMessage(), e);
        }
        return false;
    }

    public static Boolean delete(EsIndexInfo indexInfo) {
        try {
            DeleteByQueryRequest deleteByQueryRequest =
                    new DeleteByQueryRequest(indexInfo.getIndexName());
            deleteByQueryRequest.setQuery(QueryBuilders.matchAllQuery());
            BulkByScrollResponse response =
                    getClient(indexInfo.getClusterName()).deleteByQuery(deleteByQueryRequest, COMMON_OPTIONS);
            log.info("deletedSize:{}", response.getDeleted());
            return true;
        } catch (Exception e) {
            log.info("delete.exception:{}", e.getMessage());
        }
        return false;
    }

    public static boolean deleteDoc(EsIndexInfo esIndexInfo, String docId) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(esIndexInfo.getIndexName());
            deleteRequest.id(docId);
            DeleteResponse response = getClient(esIndexInfo.getClusterName()).delete(deleteRequest, COMMON_OPTIONS);
            log.info("deleteDoc.response:{}", JSON.toJSONString(response));
            return true;
        } catch (Exception e) {
            log.error("deleteDoc.exception:{}", e.getMessage(), e);
        }
        return false;
    }

    public static boolean isExistDocById(EsIndexInfo esIndexInfo, String docId) {
        try {
            GetRequest getRequest = new GetRequest(esIndexInfo.getIndexName());
            getRequest.id(docId);
            return getClient(esIndexInfo.getClusterName()).exists(getRequest, COMMON_OPTIONS);
        } catch (Exception e) {
            log.error("isExistDocById.exception:{}", e.getMessage(), e);
        }
        return false;
    }

    public static Map<String, Object> getDocById(EsIndexInfo esIndexInfo, String docId) {
        try {
            GetRequest getRequest = new GetRequest(esIndexInfo.getIndexName());
            getRequest.id(docId);
            GetResponse response = getClient(esIndexInfo.getClusterName()).get(getRequest, COMMON_OPTIONS);
            return response.getSource();
        } catch (Exception e) {
            log.error("isExistDocById.exception:{}", e.getMessage(), e);
        }
        return null;
    }

    public static Map<String, Object> getDocById(EsIndexInfo esIndexInfo, String docId,
                                                 String[] fields) {
        try {
            GetRequest getRequest = new GetRequest(esIndexInfo.getIndexName());
            getRequest.id(docId);
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, fields, null);
            getRequest.fetchSourceContext(fetchSourceContext);
            GetResponse response = getClient(esIndexInfo.getClusterName()).get(getRequest, COMMON_OPTIONS);
            return response.getSource();
        } catch (Exception e) {
            log.error("isExistDocById.exception:{}", e.getMessage(), e);
        }
        return null;
    }

    public static SearchResponse searchWithTermQuery(EsIndexInfo esIndexInfo,
                                                     EsSearchRequest esSearchRequest) {
        try {
            BoolQueryBuilder bq = esSearchRequest.getBoolQueryBuilder();
            String[] fields = esSearchRequest.getFields();
            int from = esSearchRequest.getFrom();
            int size = esSearchRequest.getSize();
            Long minutes = esSearchRequest.getMinutes();
            Boolean needScroll = esSearchRequest.getNeedScroll();
            String sortName = esSearchRequest.getSortName();

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(bq);
            searchSourceBuilder.fetchSource(fields, null).from(from).size(size);

            if (Objects.nonNull(esSearchRequest.getHighlightBuilder())) {
                searchSourceBuilder.highlighter(esSearchRequest.getHighlightBuilder());
            }

            if (StringUtils.isNotBlank(sortName)) {
                searchSourceBuilder.sort(sortName);
            }

            searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));

            SearchRequest searchRequest = new SearchRequest();
            searchRequest.searchType(SearchType.DEFAULT);
            searchRequest.indices(esIndexInfo.getIndexName());
            searchRequest.source(searchSourceBuilder);
            if (needScroll) {
                Scroll scroll = new Scroll(TimeValue.timeValueMinutes(minutes));
                searchRequest.scroll(scroll);
            }
            return getClient(esIndexInfo.getClusterName()).search(searchRequest, COMMON_OPTIONS);
        } catch (Exception e) {
            log.error("searchWithTermQuery.exception:{}", e.getMessage(), e);
        }
        return null;
    }

    public static boolean batchInsertDoc(EsIndexInfo esIndexInfo,
                                         List<EsSourceData> esSourceDataList) {
        if (log.isInfoEnabled()) {
            log.info("批量新增ES:{}", esSourceDataList.size());
            log.info("indexName:{}", esIndexInfo.getIndexName());
        }
        try {
            boolean flag = false;
            BulkRequest bulkRequest = new BulkRequest();

            for (EsSourceData source : esSourceDataList) {
                String docId = source.getDocId();
                if (StringUtils.isNotBlank(docId)) {
                    IndexRequest indexRequest = new IndexRequest(esIndexInfo.getIndexName());
                    indexRequest.id(docId);
                    indexRequest.source(source.getData());
                    bulkRequest.add(indexRequest);
                    flag = true;
                }
            }


            if (flag) {
                BulkResponse response = getClient(esIndexInfo.getClusterName()).bulk(bulkRequest, COMMON_OPTIONS);
                if (response.hasFailures()) {
                    return false;
                }
            }
        } catch (Exception e) {
            log.error("batchInsertDoc.error", e);
        }

        return true;
    }

    public static boolean updateByQuery(EsIndexInfo esIndexInfo,
                                        QueryBuilder queryBuilder,
                                        Script script,
                                        int batchSize) {
        if (log.isInfoEnabled()) {
            log.info("updateByQuery.indexName:{}", esIndexInfo.getIndexName());
        }
        try {
            UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest(esIndexInfo.getIndexName());
            updateByQueryRequest.setQuery(queryBuilder);
            updateByQueryRequest.setScript(script);
            updateByQueryRequest.setBatchSize(batchSize);
            updateByQueryRequest.setAbortOnVersionConflict(false);
            getClient(esIndexInfo.getClusterName()).updateByQuery(updateByQueryRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("updateByQuery.error", e);
        }
        return true;
    }

    /**
     * 分词方法
     */
    public static List<String> getAnalyze(EsIndexInfo esIndexInfo, String text) throws Exception {
        List<String> list = new ArrayList<>();
        Request request = new Request("GET", "_analyze");
        JSONObject entity = new JSONObject();
        entity.put("analyzer", "ik_smart");
        entity.put("text", text);
        request.setJsonEntity(entity.toJSONString());
        Response response = getClient(esIndexInfo.getClusterName()).getLowLevelClient().performRequest(request);
        JSONObject tokens = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
        JSONArray arrays = tokens.getJSONArray("tokens");
        for (int i = 0; i < arrays.size(); i++) {
            JSONObject obj = JSON.parseObject(arrays.getString(i));
            list.add(obj.getString("token"));
        }
        return list;
    }
}
