package com.viewhigh.inspection.controller;

import com.viewhigh.inspection.bean.ErrorEnum;
import com.viewhigh.inspection.bean.InspectionResp;
import com.viewhigh.inspection.bean.RepEnum;
import com.viewhigh.inspection.bean.UserDuty;
import com.viewhigh.inspection.exception.CommonException;
import com.viewhigh.inspection.service.LoginService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zhangS
 * @Description 
 * @date 2019-12-04 16:42
 */
@Slf4j
@RestController
@Api(tags = "用户登录api")
public class LoginController {

    @Autowired
    LoginService userService;

    @PostMapping("webservices/login")
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "工号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> login(@RequestParam("account") String account,@RequestParam("password") String password) {
        log.info("判断账号是否存在！");
        String sysInfo = userService.getSysInfo(account, password);
        switch (sysInfo) {
            case "8000":
                throw new CommonException(ErrorEnum.PASSWORD_ERROR.getCode(), ErrorEnum.PASSWORD_ERROR.getMsg());
            case "8001":
                throw new CommonException(ErrorEnum.ACCTOUT_ERROR.getCode(), ErrorEnum.ACCTOUT_ERROR.getMsg());
                default:return InspectionResp.getMap(null, RepEnum.REP_OK);
        }
    }

    /**
     * 获取 登app后  拥有的模块权限 
     * @param empCode
     * @return
     */
    @PostMapping("/getSysPerm")
    @ApiOperation(value = "获取权限 模块")
    @ApiImplicitParam(name = "empCode", value = "工号", required = true)
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getSysPerm(@RequestParam String empCode) {
        List<UserDuty> sysPerm = userService.getSysPerm(empCode);
        return InspectionResp.getMap(sysPerm, RepEnum.REP_OK);
    }

}
