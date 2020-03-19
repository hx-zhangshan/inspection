package com.viewhigh.inspection.serviceImp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viewhigh.inspection.dao.UserDao;
import com.viewhigh.inspection.entry.UserDemo;
import com.viewhigh.inspection.service.IUserService;
import org.springframework.stereotype.Service;


/**
 * @author zhangS
 * @Description  服务实现类
 * @date 2019-11-14 11:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserDemo> implements IUserService {

}
