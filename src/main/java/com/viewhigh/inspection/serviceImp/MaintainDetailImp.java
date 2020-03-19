package com.viewhigh.inspection.serviceImp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viewhigh.inspection.dao.MaintainDetailDao;
import com.viewhigh.inspection.entry.EquiMaintainDetail;
import com.viewhigh.inspection.service.IMaintainDetailService;
import org.springframework.stereotype.Service;

/**
 * @author zhangS
 * @Description 
 * @date 2019-12-17 15:52
 */
@Service
public class MaintainDetailImp extends ServiceImpl<MaintainDetailDao, EquiMaintainDetail> implements IMaintainDetailService  {
}
