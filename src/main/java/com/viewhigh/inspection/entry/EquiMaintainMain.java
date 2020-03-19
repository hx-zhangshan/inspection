package com.viewhigh.inspection.entry;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangS
 * @Description
 * @date 2019-12-13 9:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EquiMaintainMain extends Model<EquiMaintainMain> {
    /**
     * 唯一标示
     */
    @TableId
    @Ignore
    private Long id;
    /**
     * 编码
     */
    private String xmCode;
    /**
     * 名称
     */
    private String xmName;
    /**
     * 月/季/半年/临时
     */
    private String cycleTime;


    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 是否停用
     */
    private StatusEnum isStop;
    /**
     * 归口部门
     */
    private String att_code;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
