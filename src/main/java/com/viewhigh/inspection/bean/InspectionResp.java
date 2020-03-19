package com.viewhigh.inspection.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangS
 * @Description 
 * @date 2019-11-14 10:32
 */
@Data
@Builder
@AllArgsConstructor
@ApiModel("固定返回参数")
public class InspectionResp {

	final static public String JSON_FIELD_MSG = "respMsg";
	final static public String JSON_FIELD_CODE = "respCode";
	final static public String JSON_FIELD_DATA = "data";


	@ApiModelProperty(value="编码详情",dataType="String",name="respMsg",example="成功")
	private String respMsg;

	@ApiModelProperty(value="编码",dataType="String",name="respCode",example="200")
	private String respCode;

	@ApiModelProperty("接口内容，参见《响应消息0》，若无，则接口不返回具体内容")
	private Object data;

	@ApiModelProperty(hidden =true)
	private RepEnum repEnum;

	@ApiModelProperty(hidden =true)
	public static Map<String, Object> getMap(Object obj,RepEnum repEnum){
        //查询
		Map<String,Object> result = new HashMap<>();
		result.put(JSON_FIELD_CODE, repEnum.getCode());
		result.put(JSON_FIELD_MSG, repEnum.getMsg());
		if(obj!=null){
            result.put(JSON_FIELD_DATA, obj);
        }
		return result;
	}
    private InspectionResp() {}

    private static class InspectionRespHolder {
        private static InspectionResp instance = new InspectionResp();
    }
    @ApiModelProperty(hidden =true)
    public static InspectionResp getInstance() {
        return InspectionRespHolder.instance;
    }
}
