package com.viewhigh.inspection.bean;

import com.viewhigh.inspection.entry.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author zhangS
 * @Description 
 * @date 2019-11-14 10:32
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResp {
	String id;
	
	String code;
	
	String name;
	
	StatusEnum status;

}
