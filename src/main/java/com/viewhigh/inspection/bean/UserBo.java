package com.viewhigh.inspection.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * @author zhangS
 * @Description
 * @date 2019-12-04 17:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserBo {
    private int userId;

    private String account;

    private String password;



    private String compCode;
    private String copyCode;
    private String copyName;
}
