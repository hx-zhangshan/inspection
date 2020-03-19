package com.viewhigh.inspection;

import com.viewhigh.inspection.config.StorageProperties;
import com.viewhigh.inspection.service.StorageService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhangS
 * @Description
 * @date 2019-11-14
 * @EnableScheduling  开启定时任务
 *   创建定时任务的目的是 更新 herp端的数据到app 后端 （webServer）
 */
@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@EnableConfigurationProperties({StorageProperties.class})
@EnableScheduling
public class InspectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(InspectionApplication.class, args);
    }

    /**
     * 启动springBoot服务后进行的方法，可以进行数据准备
     * 这里进行上传文件夹的创建，确保文件夹的存在。
     * @param storageService
     * @return
     */
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            //初始化的时候删除全部文件？
//            storageService.deleteAll();
            //若是不存在 文件加 会自动创建
            storageService.init();
        };
    }
}
