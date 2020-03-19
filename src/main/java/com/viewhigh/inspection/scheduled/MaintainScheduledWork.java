package com.viewhigh.inspection.scheduled;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.viewhigh.inspection.entry.*;
import com.viewhigh.inspection.service.IDetailService;
import com.viewhigh.inspection.service.IMainService;
import com.viewhigh.inspection.service.IMaintainDetailService;
import com.viewhigh.inspection.service.IMaintainService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author zhangS
 * @Description
 * @date 2019-12-17 17:29
 * @description 这个是专门做的 保养的 定时任务
 */
@Component
public class MaintainScheduledWork extends IScheduledWork {
    @Autowired
    IMaintainService maintainService;
    @Autowired
    IMaintainDetailService maintainDetailService;
    @Autowired
    IMainService mainService;
    @Autowired
    IDetailService detailService;

    @Override
    public void scheduledWork_month(String se) {
        //查出月数据
        LambdaQueryWrapper<EquiMaintainMain> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(EquiMaintainMain::getCycleTime, se).
                eq(EquiMaintainMain::getIsStop, 0);
        List<EquiMaintainMain> ems = maintainService.list(lambdaQueryWrapper);
        EquiMaintainMainWork ew = new EquiMaintainMainWork();
        for (EquiMaintainMain em : ems) {
            BeanUtils.copyProperties(em, ew);
            ew.setCreateTime(new Date());
            //定时任务生成 都为admin
            ew.setOperMan("admin");
            ew.setIsClose(StatusEnum.DISABLE);
            mainService.save(ew);
            //通过主表id 查出附表的所有资产
            LambdaQueryWrapper<EquiMaintainDetail> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(EquiMaintainDetail::getMainId, em.getId());
            List<EquiMaintainDetail> eds = maintainDetailService.list(queryWrapper);
            EquiMaintainDetailWork edw = new EquiMaintainDetailWork();
            eds.forEach(ed -> {
                BeanUtils.copyProperties(ed, edw);
                edw.setState(StatusDetail.NOORDER).setMainId(ew.getId());
                detailService.save(edw);
            });

        }
    }

    @Override
    public void scheduledWork_quarter(String se) {
        scheduledWork_month(se);
    }

    @Override
    public void scheduledWork_halfYear(String se) {
        scheduledWork_month(se);
    }

    @Override
    public void scheduledWork_year(String se) {
        scheduledWork_month(se);
    }
}
