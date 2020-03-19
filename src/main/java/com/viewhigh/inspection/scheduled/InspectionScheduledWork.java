package com.viewhigh.inspection.scheduled;

import com.viewhigh.inspection.service.IDetailService;
import com.viewhigh.inspection.service.IMainService;
import com.viewhigh.inspection.service.IMaintainDetailService;
import com.viewhigh.inspection.service.IMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangS
 * @Description 
 * @date 2019-12-17 17:29
 * @description 这个是专门为 巡检的 定时任务
 */
@Component
public class InspectionScheduledWork extends IScheduledWork {
    @Autowired
    IMaintainService maintainService;
    @Autowired
    IMaintainDetailService maintainDetailService;
    @Autowired
    IMainService mainService;
    @Autowired
    IDetailService detailService;
    @Override
    public void scheduledWork_month(String se){
//        //查出月数据
//        LambdaQueryWrapper<EquiMaintainMain> lambdaQueryWrapper = Wrappers.lambdaQuery();
//        lambdaQueryWrapper.eq(EquiMaintainMain::getCycleTime, se).
//                eq(EquiMaintainMain::getIsStop, 0);
//        List<EquiMaintainMain> ems = maintainService.list(lambdaQueryWrapper);
//        EquiMaintainMainWork ew = new EquiMaintainMainWork();
//        for (EquiMaintainMain em : ems) {
//            BeanUtils.copyProperties(em, ew);
//            ew.setCreateTime(new Date());
//            //定时任务生成 都为admin
//            ew.setOperMan("admin");
//            ew.setIsClose(StatusEnum.DISABLE);
//            mainService.save(ew);
//            //通过主表id 查出附表的所有资产
//            Long id = em.getId();
//            LambdaQueryWrapper<EquiMaintainDetail> queryWrapper = Wrappers.lambdaQuery();
//            queryWrapper.eq(EquiMaintainDetail::getMainId, id);
//            List<EquiMaintainDetail> eds = maintainDetailService.list(queryWrapper);
//            EquiMaintainDetailWork edw = new EquiMaintainDetailWork();
//            eds.forEach(ed -> {
//                BeanUtils.copyProperties(ed, edw);
//                edw.setState(StatusDetail.NOORDER).setMainId(ew.getId());
//                detailService.save(edw);
//            });
//
//        }
    }

    @Override
    public void scheduledWork_quarter(String se){

    }
    @Override
    public void scheduledWork_halfYear(String se){

    }
    @Override
    public void scheduledWork_year(String se){

    }
}
