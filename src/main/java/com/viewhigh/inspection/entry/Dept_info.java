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
public class Dept_info {

    /**
     * 唯一标示
     */
//    @TableId
    private String deptCode;
    /**
     * 所在科室
     */
    private String deptName;


    private String state;


}
