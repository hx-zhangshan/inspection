package com.viewhigh.inspection.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viewhigh.inspection.bean.UserBo;
import com.viewhigh.inspection.bean.UserDuty;
import com.viewhigh.inspection.entry.SysUser;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {

	String getUser(UserBo sysUser);

	String getPassWord(UserBo sysUser);


	List<UserDuty> getSysPerm(String userCode);

}

