package com.viewhigh.inspection.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.viewhigh.inspection.bean.InspectionResp;
import com.viewhigh.inspection.bean.RepEnum;
import com.viewhigh.inspection.entry.Dept_info;
import com.viewhigh.inspection.entry.EmpInfo;
import com.viewhigh.inspection.entry.EquiCardInfo;
import com.viewhigh.inspection.entry.EquiInspectionDetailWork;
import com.viewhigh.inspection.exception.CommonException;
import com.viewhigh.inspection.service.InspectionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * @author zhangS
 * @Description 
 * @date 2019-12-11 10:01
 */
@Slf4j
@RestController
@RequestMapping("/inspection")
@Api(tags = "巡检App的api")
public class InspectionController {

    @Autowired
    InspectionService detailService;

    @GetMapping("/getInspectionDeptList/{isInspection}")
    @ApiOperation(value = "获取巡检列表通过部门分组 0 未巡检 其他已经巡检")
    @ApiImplicitParam(name = "isInspection", value = "查询isInspection", required = true)
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getInspectionDeptList(@PathVariable("isInspection") int isInspection) {
        List<Dept_info> deptCodes = detailService.getInspectionDeptList(isInspection);
        //查询
        return InspectionResp.getMap(deptCodes, RepEnum.REP_OK);
    }

    @GetMapping("/getInspectionEmpList")
    @ApiOperation(value = "获取要分派的工程师")
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getInspectionEmpList() {
        List<EmpInfo> deptCodes = detailService.getInspectionEmpList();
        //查询
        return InspectionResp.getMap(deptCodes, RepEnum.REP_OK);
    }

    /**
     *
     * @param deptCodes
     * @param empCode
     * @return
     */
    @PostMapping("/saveInspectionEmpCode")
    @ApiOperation(value = "给科室分派工程师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCodes", value = "选择要巡检的科室,数组", required = true),
            @ApiImplicitParam(name = "empCode", value = "选择的工程师", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> saveInspectionEmpCode(@RequestParam("deptCodes") List<String> deptCodes, @RequestParam("empCode") String empCode) {
        Iterator<String> iterator = deptCodes.iterator();
        while (iterator.hasNext()){
            String deptCode = iterator.next();
            detailService.saveInspectionEmpCode(deptCode, empCode);
        }

        //查询
        return InspectionResp.getMap(null, RepEnum.REP_OK);
    }

    /**
     * 获取人员属性 用来判断是调度人员还是工程师
     */
    @PostMapping("/isHeadEmpCode")
    @ApiOperation(value = "获取人员属性(调度人员或工程师)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empCode", value = "工号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> isHeadEmpCode(@RequestParam("empCode") String empCode) {
        List<EmpInfo> empInfos = detailService.isHeadEmpCode(empCode);
        //查询
        return InspectionResp.getMap(empInfos, RepEnum.REP_OK);
    }

    @PostMapping("/getEquiCardListByDeptCode")
    @ApiOperation(value = "调度中心获取资产")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCode", value = "科室编码", required = true),
            @ApiImplicitParam(name = "state", value = "未处理", required = true),
            @ApiImplicitParam(name = "isUnusual", value = "是否异常", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getEquiCardListByDeptCode(@RequestParam("deptCode") String deptCode, int state, int isUnusual) {
        List<EquiCardInfo> empInfos = detailService.getEquiCardListByDeptCode(deptCode, state, isUnusual);
        //查询
        return InspectionResp.getMap(empInfos, RepEnum.REP_OK);
    }

    @PostMapping("/recallDeptCode")
    @ApiOperation(value = "撤回已处理科室")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCodes", value = "科室编码,数组", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    @Transactional(rollbackFor = CommonException.class)
    public Map<String, Object> recallDeptCode(@RequestParam("deptCodes") List<String> deptCodes) {
        Iterator<String> iterator = deptCodes.iterator();
        while (iterator.hasNext()) {
            String deptCode = iterator.next();
            int i = detailService.recallDeptCode(deptCode);
            if(i==0){
                throw new CommonException("400", deptCode+" 此科室有数据不在可撤销状态。");
            }
        }
        //查询
        return  InspectionResp.getMap(null, RepEnum.REP_OK);
    }

    @PostMapping("/getProjectList")
    @ApiOperation(value = "获取工单信息（工程师）")
    @ApiImplicitParam(name = "empCode", value = "工号")
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getProjectList(String empCode) {
        List<Dept_info> projectList = detailService.getProjectList(empCode);
        //查询
        return InspectionResp.getMap(projectList, RepEnum.REP_OK);
    }

    @PostMapping("/orderProject")
    @ApiOperation(value = "工程师接单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCode", value = "科室编码", required = true),
            @ApiImplicitParam(name = "empCode", value = "工号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> orderProject(@RequestParam("deptCode") String deptCode, @RequestParam("empCode") String empCode) {
        detailService.orderProject(deptCode, empCode);
        //查询
        return InspectionResp.getMap(null, RepEnum.REP_OK);
    }

    @PostMapping("/recallProject")
    @ApiOperation(value = "工程师退回")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCode", value = "科室编码", required = true),
            @ApiImplicitParam(name = "empCode", value = "工号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> recallProject(@RequestParam("deptCode") String deptCode, @RequestParam("empCode") String empCode) {
        int i = detailService.recallProject(deptCode, empCode);
        //查询
        return i == 0 ? InspectionResp.getMap(null, RepEnum.REP_NO_DATA) : InspectionResp.getMap(null, RepEnum.REP_OK);
    }

    /**
     * @param deptCode
     * @return
     * @descrition 工程师只能查询 分派给她自己的 资产
     */
    @PostMapping("/getEquiCardListByDeptCode2")
    @ApiOperation(value = "工程师查询自己的资产")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCode", value = "科室编码", required = true),
            @ApiImplicitParam(name = "empCode", value = "工号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getEquiCardListByDeptCode2(@RequestParam("deptCode") String deptCode, @RequestParam("empCode") String empCode) {
        List<EquiCardInfo> empInfos = detailService.getEquiCardListByDeptCode2(deptCode, empCode);
        //查询
        return InspectionResp.getMap(empInfos, RepEnum.REP_OK);
    }

    /**
     * @param
     * @return
     * @descrition 插入巡检内容和方式
     */
    @PostMapping("/insertInspectionInfo")
    @ApiOperation(value = "插入巡检内容和方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "equiArchNo", value = "资产卡号", required = true),
            @ApiImplicitParam(name = "InspectionSelf", value = "巡检内容", required = true),
            @ApiImplicitParam(name = "remarks", value = "问题描述", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> insertInspectionInfo(@RequestParam("equiArchNo") String equiArchNo,
                                                  @RequestParam("InspectionSelf") String InspectionSelf,
                                                  @RequestParam("remarks") String remarks) {
//        detailService.insertInspectionInfo(equiArchNo, InspectionSelf,InspectionWay,remarks);
//是否需要先判断 状态
        UpdateWrapper<EquiInspectionDetailWork> queryWrapper = new UpdateWrapper<>();
        queryWrapper.lambda().eq(EquiInspectionDetailWork::getEquiArchNo, equiArchNo);
        EquiInspectionDetailWork equiInspectionDetailWork = new EquiInspectionDetailWork().setInspectionSelf(InspectionSelf).
                setRemarks(remarks);
        detailService.update(equiInspectionDetailWork, queryWrapper);

        //查询
        return InspectionResp.getMap(null, RepEnum.REP_OK);
    }
    /**
     * @param deptCode
     * @return
     * @descrition 工程师完工
     */
    @PostMapping("/endProject")
    @ApiOperation(value = "工程师完工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCode", value = "科室编码", required = true),
            @ApiImplicitParam(name = "empCode", value = "工号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> endProject(@RequestParam("deptCode") String deptCode, @RequestParam("empCode") String empCode) {
         detailService.endProject(deptCode, empCode);
        //查询
        return InspectionResp.getMap(null, RepEnum.REP_OK);
    }
    /**
     * @param empCode
     * @return
     * @descrition 工程师 - 历史列表
     */
    @PostMapping("/getProjectHistoryList")
    @ApiOperation(value = "工程师-历史列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empCode", value = "工号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getProjectHistoryList( @RequestParam("empCode") String empCode) {
        List<Dept_info> projectHistoryList = detailService.getProjectHistoryList(empCode);
        //查询
        return InspectionResp.getMap(projectHistoryList, RepEnum.REP_OK);
    }
}
