package com.anlythree.anlyes.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;


/**
 * @Description:
 * @Author: jinhaoxun
 * @Date: 2020/7/8 4:23 下午
 * @Version: 1.0.0
 */
@AutoConfiguration
public class ElasticSearchClientConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                // 默认连接本地的9200端口
            RestClient.builder(new HttpHost("localhost", 9200, "http")));
        return restHighLevelClient;
    }

}
