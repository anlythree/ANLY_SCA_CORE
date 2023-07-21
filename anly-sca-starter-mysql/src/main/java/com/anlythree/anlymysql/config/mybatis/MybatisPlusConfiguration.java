package com.anlythree.anlymysql.config.mybatis;

import com.anlythree.anlymysql.config.mybatis.injector.AnlySqlInjector;
import com.anlythree.anlymysql.config.mybatis.interceptor.SqlLogInterceptor;
import com.anlythree.anlymysql.config.props.AnlyMybatisProperties;
import com.anlythree.common.factory.YamlPropertySourceFactory;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * mybatis plus配置中心
 *
 * @author xuzhanfu
 * @author L.cm
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@AllArgsConstructor
@EnableTransactionManagement
@EnableConfigurationProperties(AnlyMybatisProperties.class)
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:anly-mybatisplus.yml")
@MapperScan({"com.anlythree.**.mapper.**","com.anlythree.**.bussiness.*.dao"})
public class MybatisPlusConfiguration {


	/**
	 * sql 注入
	 */
	@Bean
	public ISqlInjector sqlInjector() {
		return new AnlySqlInjector();
	}

	/**
	 * sql 日志
	 */
	@Bean
//	@Profile({"local", "dev", "test"})
	@ConditionalOnProperty(value = "anly.mybatis.sql")
	public SqlLogInterceptor sqlLogInterceptor() {
		return new SqlLogInterceptor();
	}



}
