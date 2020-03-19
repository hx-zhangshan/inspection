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
public class EquiMaintainDetailWork extends Model<EquiMaintainDetailWork>

{
    private static final long serialVersionUID = 1L;

    /**
     * 唯一标示
     */
    @TableId(type = IdType.AUTO)
    private Long detailId;
    /**
     * 编码
     */
    private Long mainId;
    /**
     * 名称
     */
    private String equiArchNo;
    /**
     * 调度中心未派单、调度中心已派单、工程师已接单、执行完毕
     */
    private StatusDetail state;

    /**
     * 巡检工程师
     */
    private String inspectionMan;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 内容
     */
    private String maintainSelf;
    /**
     * 保养方式
     */
    private String maintainWay;
    /**
     * 图片
     */
    private String filePath;
    public static final String DETAIL_ID = "detail_id";

    public static final String MAIN_ID ="main_id";

    public static final String EQUI_ARCH_NO = "equi_arch_no";

    public static final String INSPECTION_MAN = "inspection_man";


    @Override
    protected Serializable pkVal() {
        return this.detailId;
    }
}
