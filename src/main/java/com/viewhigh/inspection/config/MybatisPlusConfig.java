package com.viewhigh.inspection.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author zhangS
 * @Description 
 * @date 2019-11-14 11:48
 */
@Configuration
@ImportResource(locations = {"classpath:/mybatis/spring-mybatis.xml"})
//@MapperScan("com.viewhigh.inspection.dao")
//@EnableTransactionManagement
public class MybatisPlusConfig {

}
