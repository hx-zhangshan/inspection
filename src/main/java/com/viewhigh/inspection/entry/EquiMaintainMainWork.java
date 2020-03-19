package com.viewhigh.inspection.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @date 2019-11-14 14:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EquiMaintainMainWork extends Model<EquiMaintainMainWork>

{
    private static final long serialVersionUID = 1L;

    /**
     * 唯一标示
     */
    @TableId(type = IdType.AUTO)
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String operMan;
    /**
     * 是否关闭
     */
    private StatusEnum isClose;


    public static final String ID = "id";

    public static final String XM_CODE ="xm_code";

    public static final String XM_NAME = "xm_name";

    public static final String CYCLE_TIME = "cycle_time";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
