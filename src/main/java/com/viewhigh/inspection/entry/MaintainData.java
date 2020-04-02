package com.viewhigh.inspection.entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * @author zhangS
 * @Description 
 * @date 2020-04-02 11:04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MaintainData {

    /**
     * 工号
     */
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    private String infoCode;
    /**
     * 姓名
     */
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    private String infoName;
    /**
     * 姓名
     */
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    private String infoData;



}
