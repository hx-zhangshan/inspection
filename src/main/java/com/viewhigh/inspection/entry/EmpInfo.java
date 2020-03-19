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
public class EmpInfo {

    /**
     * 工号
     */
    private String empCode;
    /**
     * 姓名
     */
    private String empName;
    /**
     * 属性 工程师
     * @JsonInclude 值为空就不进行转换
     * 可在配置文件中 配置
     *  //@JsonInclude(value=JsonInclude.Include.NON_NULL)
     */
    private String equiDuty;



}
