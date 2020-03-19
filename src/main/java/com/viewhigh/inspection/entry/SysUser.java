package com.viewhigh.inspection.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SysUser {
	
	private int userId;
	
	private String account;
	
	private String password;

	

	private String compCode;
	private String copyCode;
	private String copyName;

	
}
