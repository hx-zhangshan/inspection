package com.viewhigh.inspection.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @author zhangS
 * @Description 
 * @date 2019-12-10 16:01
 */
@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	private String location = "upload-dir";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
