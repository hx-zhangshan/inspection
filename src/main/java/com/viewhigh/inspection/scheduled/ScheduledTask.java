package com.viewhigh.inspection.scheduled;

import com.viewhigh.inspection.bean.ScheduledEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author zhangS
 * @Description
 * @date 2019-12-11 17:33
 * @description 这个类 主要为了实现 类似sqlServer的 自定作业功能， 同步herp端数据到app后端。
 */
@Component
@Slf4j
@PropertySource("classpath:time.properties")
public class ScheduledTask {

    @Autowired
    ScheduledWorkList scheduledWork;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 按照月 频率 执行
     * 保养APP的定时数据同步
     *
     *  每月月初进行 扫描herp表 发现 除了临时的 任务 其他月 季度，半年 的
     * 项目 都进行 创建/
     */
    @Scheduled(cron = "${time.month.cron}")
    public void reportCurrentTime() {
        log.info("按照月循環的项目：The time is now {}", dateFormat.format(new Date()));
        //只查询库里是  循环时间是月的 然后进行 插入。
        scheduledWork.exc(ScheduledEnum.MONTH);
    }

    /**
     * 按照 季度执行 一月第一天 三月最后一天 类推
     * 巡检APP的定时数据同步
     *
     */
    @Scheduled(cron = "${time.quarter.cron}")
//    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime_copy() {
        log.info("按照季度循環的项目The time now {}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        scheduledWork.exc(ScheduledEnum.QUARTER);
    }

    /**
     * 按照半年的 频率执行 1月1 到 6月31
     * 巡检APP的定时数据同步
     *
     * @TODO
     */
    @Scheduled(cron = "${time.halfYear.cron}")
    public void year_work1() {
        log.info("按照半年循環的项目The time now {}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        scheduledWork.exc(ScheduledEnum.HALFYEAR);
    }

    /**
     * 按照年的 频率执行 1月1 到 12月31
     *
     * @TODO
     */
    @Scheduled(cron = "${time.year.cron}")
    public void year_work2() {
        log.info("按照年循環的项目The time now {}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        scheduledWork.exc(ScheduledEnum.YEAR);
    }

}
