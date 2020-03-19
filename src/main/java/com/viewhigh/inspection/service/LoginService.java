package com.viewhigh.inspection.service;

import com.viewhigh.inspection.bean.UserDuty;

import java.util.List;

/**
 * @author zhangS
 * @Description 
 * @date 2019-12-04 17:11
 */
public interface LoginService {

	String getSysInfo(String account, String password);

	List<UserDuty> getSysPerm(String empCode);

}
