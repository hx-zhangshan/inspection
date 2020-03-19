package com.viewhigh.inspection.serviceImp;

import com.alibaba.druid.util.StringUtils;
import com.viewhigh.inspection.bean.ErrorEnum;
import com.viewhigh.inspection.bean.RepEnum;
import com.viewhigh.inspection.bean.UserBo;
import com.viewhigh.inspection.bean.UserDuty;
import com.viewhigh.inspection.dao.SysUserMapper;
import com.viewhigh.inspection.service.LoginService;
import com.viewhigh.inspection.util.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangS
 * @Description
 * @date 2019-12-04 17:16
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public String getSysInfo(String account, String password) {
		UserBo user=new UserBo();
		user.setAccount(account).setPassword(Encrypt.Md5(password));
		String passWord = sysUserMapper.getPassWord(user);
		if(StringUtils.isEmpty(passWord)){
			log.error("用户名不存在!");
			return ErrorEnum.ACCTOUT_ERROR.getCode();
		}
		String userName = sysUserMapper.getUser(user);
		if(StringUtils.isEmpty(userName)){
			log.error("用户密码错误!");
			return ErrorEnum.PASSWORD_ERROR.getCode();
		}
		return RepEnum.REP_OK.getCode()+"";
	}
	@Override
	public List<UserDuty> getSysPerm(String empCode) {
		List<UserDuty> sysPerm = sysUserMapper.getSysPerm(empCode);

		return sysPerm;
	}

}
