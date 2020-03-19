package com.viewhigh.inspection.bean;

import java.io.Serializable;
import java.util.List;

public class ResponseJsonForLogin implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String status;
	private String isSuccess;
	private String message;
	private Result result;
	
	
	public static class Result
	{
		
		private int platformType;
		private String accountName;
		private String deptId;
		private String compCode;
		private List<UserDuty> userDuty;
		private String newServerPort;
		private String account;

		
		public Result(String user, int i,String deptId,List<UserDuty> userDuty,String compCode,String newServerPort,String account) {
			this.accountName = user;
			this.platformType = i;
			this.deptId = deptId;
			this.userDuty =userDuty;
			this.compCode = compCode;
			this.newServerPort = newServerPort;
			this.account = account;
		}
		public int getPlatformType() {
			return platformType;
		}
		public void setPlatformType(int platformType) {
			this.platformType = platformType;
		}
		public String getAccountName() {
			return accountName;
		}
		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}
		public String getDeptId() {
			return deptId;
		}
		public void setDeptId(String deptId) {
			this.deptId = deptId;
		}
		public String getCompCode() {
			return compCode;
		}
		public void setCompCode(String compCode) {
			this.compCode = compCode;
		}
		public List<UserDuty> getUserDuty() {
			return userDuty;
		}
		public void setUserDuty(List<UserDuty> userDuty) {
			this.userDuty = userDuty;
		}
		public String getNewServerPort() {
			return newServerPort;
		}
		public void setNewServerPort(String newServerPort) {
			this.newServerPort = newServerPort;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		
		
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getIsSuccess() {
		return isSuccess;
	}


	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Result getResult() {
		return result;
	}


	public void setResult(Result result) {
		this.result = result;
	}
}
