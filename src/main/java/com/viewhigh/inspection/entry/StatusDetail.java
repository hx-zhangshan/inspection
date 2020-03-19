package com.viewhigh.inspection.entry;

/**
 * @author zhangS
 * @Description 
 * @date 2019-11-14 10:30
 */
public enum StatusDetail {
	
	NOORDER(0,"调度中心未派单"),
	ALREDARYSENT(1,"调度中心已派单"),
	ORDERRECEIVED(2,"工程师已接单"),
	FINISHED(3,"执行完毕");
	private String msg;
	private int code;

	private StatusDetail(int code,String msg){
		this.code=code;
		this.msg=msg;
	}

	public String getMsg() {
		return msg;
	}
	public int getCode() {
		return code;
	}
	public static String getMsgByCode(int code) {
		switch (code) {
			case 0:
				return NOORDER.getMsg();
			case 1:
				return ALREDARYSENT.getMsg();
			case 2:
				return ORDERRECEIVED.getMsg();
			default:
				return FINISHED.getMsg();
		}
	}
}
