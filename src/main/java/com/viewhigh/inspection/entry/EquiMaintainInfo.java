package com.viewhigh.inspection.entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

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
public class EquiMaintainInfo {
    /**
     * 资产卡号
     */
    private String equiArchNo;
    /**
     * 资产名称
     */
    private String detailId;
    /**
     * 项目编码
     */
    private String xmCode;
    /**maintainData
     *
     */
    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    private List<MaintainData> maintainDatas;

}
