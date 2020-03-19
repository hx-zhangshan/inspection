package com.viewhigh.inspection.serviceImp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viewhigh.inspection.dao.MainDao;
import com.viewhigh.inspection.entry.EquiMaintainMainWork;
import com.viewhigh.inspection.service.IMainService;
import org.springframework.stereotype.Service;


/**
 * @author zhangS
 * @Description  服务实现类
 * @date 2019-11-14 11:49
 */
@Service
public class MainServiceImpl extends ServiceImpl<MainDao, EquiMaintainMainWork> implements IMainService{

}
