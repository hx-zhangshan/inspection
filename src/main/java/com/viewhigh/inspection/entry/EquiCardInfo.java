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
 * @date 2019-11-26 11:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EquiCardInfo {
    /**
     * 资产卡号
     */
    private String equiArchNo;
    /**
     * 资产名称
     */
    private String equiName;
    /**
     * 规格
     */
    private String equiSpec;
    /**
     * 型号
     */
    private String equiModel;
    /**
     * 原值
     */
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    private float primMoney;
    /**
     * 问题描述
     */
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    private String remarks;
    /**
     * 保养方式
     */
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    private String maintainWay;
    /**
     * 保养内容
     */
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    private String maintainSelf;
    /**
     * 所在科室
     */
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    private String deptName;

    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    private StatusDetail state;

    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    private String detailId;
}
