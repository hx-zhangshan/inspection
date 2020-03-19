package com.viewhigh.inspection.serviceImp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viewhigh.inspection.dao.MaintainDao;
import com.viewhigh.inspection.entry.EquiMaintainMain;
import com.viewhigh.inspection.service.IMaintainService;
import org.springframework.stereotype.Service;

/**
 * @author zhangS
 * @Description 
 * @date 2019-12-17 15:52
 */
@Service
public class MaintainServiceImp extends ServiceImpl<MaintainDao, EquiMaintainMain> implements IMaintainService {
}
