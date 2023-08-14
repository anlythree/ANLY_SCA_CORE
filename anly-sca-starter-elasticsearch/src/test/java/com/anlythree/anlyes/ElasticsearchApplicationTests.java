package com.anlythree.anlyes;

import com.anlythree.anlyes.entity.User;
import com.anlythree.anlyes.service.ElasticSearchService;
import com.anlythree.anlyes.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest
class ElasticsearchApplicationTests {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Test
    void createIndexTest() throws Exception {
        // 创建索引
        elasticSearchService.createIndex("test_index");
    }

    @Test
    void existIndexTest() throws Exception {
        // 判断索引是否存在
        boolean testIndex = elasticSearchService.existIndex("test_index");
        System.out.println("是否存在："+testIndex);
    }

    @Test
    void deleteIndexTest() throws Exception {
        // 删除索引
        boolean testIndex = elasticSearchService.deleteIndex("test_index");
        System.out.println("删除结果："+testIndex);
    }

    @Test
    void addDocumentTest() throws Exception {
        // 新增文档
        User user = new User();
        user.setId(1);
        user.setAge(12);
        user.setName("测试name");
        user.setDescription("测试des");

        boolean testIndex = elasticSearchService.addDocument("test_index", user.getId().toString(), JsonUtils.objectToJson(user));
        System.out.println("新增文档结果："+testIndex);
    }

    @Test
    void isExistsDocumentTest() throws Exception {
        // 判断是否存在文档
        boolean testIndex = elasticSearchService.isExistsDocument("test_index", "1");
        System.out.println("是否存在文档查询结果："+testIndex);
    }

    @Test
    void getDocumentTest() throws Exception {
        // 获取文档
        String testIndex = elasticSearchService.getDocument("test_index", "1");
        System.out.println("获取文档信息："+testIndex);

    }

    @Test
    void updateDocumentTest() throws Exception {
        // 更新文档
        User user = new User();
        user.setId(1);
        user.setAge(33);
        user.setName("测试name update");
        user.setDescription("测试des update");

        boolean testIndex = elasticSearchService.updateDocument("test_index", user.getId().toString(), JsonUtils.objectToJson(user));
        System.out.println("更新文档结果："+testIndex);
    }

    @Test
    void deleteDocumentTest() throws Exception {
        // 删除文档
        boolean testIndex = elasticSearchService.deleteDocument("test_index", "1");
        System.out.println("删除文档结果："+testIndex);
    }

    @Test
    void bulkRequestTest() throws Exception {
        // 批量插入
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setId(i);
            user.setAge(i);
            user.setName("批量新增测试name第" + i+"个");
            user.setDescription("批量新增测试des第" + i+"个");
            users.add(user);
        }

        boolean testIndex = elasticSearchService.bulkRequest("test_index", users);
        System.out.println("批量插入文档结果："+testIndex);
    }

    @Test
    void searchRequestTest() throws Exception {
        // 搜索请求
        List<Map<String, Object>> maps =
                elasticSearchService.searchRequest("test_index", "测试");
        System.out.println("搜索请求结果："+maps);
    }

    @Test
    void searchAllRequestTest() throws Exception {
        // 搜索请求
        List<Integer> testIndex = elasticSearchService.searchAllRequest("test_index");
        System.out.println("搜索所有结果："+testIndex);
    }

    @BeforeEach
    void testBefore(){
        log.info("测试开始!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @AfterEach
    void testAfter(){
        log.info("测试结束!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

}
