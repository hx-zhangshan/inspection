package com.viewhigh.inspection.controller;

import com.viewhigh.inspection.bean.RepEnum;
import com.viewhigh.inspection.bean.UserReq;
import com.viewhigh.inspection.bean.UserResp;
import com.viewhigh.inspection.entry.UserDemo;
import com.viewhigh.inspection.exception.CommonException;
import com.viewhigh.inspection.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangS
 * @Description
 * @date 2019-11-14 9:44
 */
@Slf4j
@RestController
@RequestMapping("/user")
@ApiIgnore
//@Api(tags = "用户api")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("add")
    @ApiOperation(value = "用户新增")
    //正常业务时， 需要在user类里面进行事务控制，控制层一般不进行业务控制的。
    //@Transactional(rollbackFor = Exception.class)
    public Map<String, String> addUser(@Valid @RequestBody UserReq userReq) {

        UserDemo userDemo = new UserDemo();
        userDemo.setCode(userReq.getCode());
        userDemo.setName(userReq.getName());
//        由于设置了主键策略 id可不用赋值 会自动生成
        userService.save(userDemo);
        Map<String, String> result = new HashMap<>();
        result.put("respCode", "200");
        result.put("respMsg", "新增成功");
        //事务测试
//        System.out.println(1/0);
        log.info("添加用户：{}({})", userDemo.toString());
        return result;
    }

    @PostMapping("update")
    @ApiOperation(value = "用户修改")
    public Map<String, String> updateUser(@Valid @RequestBody UserReq userReq) {

        if (userReq.getId() == null || "".equals(userReq.getId())) {
            throw new CommonException("0000", "更新时ID不能为空");
        }
        UserDemo userDemo = new UserDemo();
        userDemo.setCode(userReq.getCode());
        userDemo.setName(userReq.getName());
        userDemo.setId(Long.parseLong(userReq.getId()));
        userService.updateById(userDemo);
        Map<String, String> result = new HashMap<String, String>();
        result.put("respCode", "01");
        result.put("respMsg", "更新成功");
        return result;
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "用户查询(ID)")
    @ApiImplicitParam(name = "id", value = "查询ID", required = true)
    public Map<String, Object> getUser(@PathVariable("id") String id) {
        //查询
        UserDemo userDemo = userService.getById(id);
        if (userDemo == null) {
            throw new CommonException("0001", "用户ID：" + id + "，未找到");
        }
//        User user=new User(1L,"code","zs",StatusEnum.DISABLE,new Date(),new Date());
        UserResp resp = UserResp.builder()
                .id(userDemo.getId().toString())
                .code(userDemo.getCode())
                .name(userDemo.getName())
                .status(userDemo.getStatus())
                .build();
        Map<String, Object> result = new HashMap<>();
        result.put("respCode", RepEnum.REP_OK.getCode());
        result.put("respMsg", RepEnum.REP_OK.getMsg());
        result.put("data", resp);
        log.info("#######################################3");
        return result;
    }
}
