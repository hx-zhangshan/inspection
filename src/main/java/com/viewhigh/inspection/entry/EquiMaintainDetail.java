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
 * @date 2019-12-13 9:41
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EquiMaintainDetail extends Model<EquiMaintainDetail>{
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
     * 卡号
     */
    private String equiArchNo;

    @Override
    protected Serializable pkVal() {
        return this.detailId;
    }
}
