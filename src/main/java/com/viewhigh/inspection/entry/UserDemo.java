package com.viewhigh.inspection.entry;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zhangS
 * @Description
 * @date 2019-11-14 10:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDemo extends Model<UserDemo>

    {
    private static final long serialVersionUID = 1L;

    /**
     * 唯一标示
     */
//    @TableId
    private Long id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;

    /**
     * 状态1 启用 0 停用
     */
    private StatusEnum status;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;


    public static final String ID = "id";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFIED = "gmt_modified";

//    @Override
//    protected Serializable pkVal() {
//        return this.id;
//    }
}
