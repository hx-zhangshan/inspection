package com.viewhigh.inspection.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.viewhigh.inspection.bean.InspectionResp;
import com.viewhigh.inspection.bean.RepEnum;
import com.viewhigh.inspection.bean.UserDuty;
import com.viewhigh.inspection.entry.*;
import com.viewhigh.inspection.exception.CommonException;
import com.viewhigh.inspection.service.IDetailService;
import com.viewhigh.inspection.service.IMainService;
import com.viewhigh.inspection.service.StorageService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zhangS
 * @Description
 * @date 2019-11-14 9:44
 */
@Slf4j
@RestController
@RequestMapping("/maintain")
@Api(tags = "保养App的api")
public class MainController {

    @Autowired
    IMainService mainService;
    @Autowired
    IDetailService detailService;

    @Autowired
    StorageService storageService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    @ApiOperation(value = "文件上传")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "files", value = "文件", required = true),
            @ApiImplicitParam(name = "equiArchNo", value = "资产卡号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> handleFileUpload_T( MultipartFile[] file,@RequestParam("equiArchNo") String equiArchNo ) throws IOException {
        //修改文件名称
        StringBuffer names=new StringBuffer("");
        //存文件 到系统 文件夹下 upload-dir
        for (MultipartFile fileInfo:file){
            String name = storageService.store(fileInfo);
            names.append(name);
            names.append(";");
        }

        //生成 特定的文件名 和路径存在数据库
        UpdateWrapper<EquiMaintainDetailWork> queryWrapper = new UpdateWrapper<>();
        queryWrapper.lambda().eq(EquiMaintainDetailWork::getEquiArchNo, equiArchNo);
        EquiMaintainDetailWork equiMaintainDetailWork = new EquiMaintainDetailWork().setFilePath(names.toString());
        detailService.update(equiMaintainDetailWork, queryWrapper);
        return InspectionResp.getMap(null, RepEnum.REP_OK);
    }

    @GetMapping("/getMaintainDeptList/{isMaintain}")
    @ApiOperation(value = "获取保养列表通过部门分组 0 未保养 其他已经保养")
    @ApiImplicitParam(name = "isMaintain", value = "查询isMaintain", required = true)
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getMaintainDeptList(@PathVariable("isMaintain") int isMaintain) {
        List<Dept_info> deptCodes = detailService.getMaintainDeptList(isMaintain);
        //查询
        return InspectionResp.getMap(deptCodes, RepEnum.REP_OK);
    }
    @GetMapping("/getMaintainDeptList_endWork")
    @ApiOperation(value = "获取调度中心已完工保养列表通过部门分组 ")
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getMaintainDeptList_endWork() {
        List<Dept_info> deptCodes = detailService.getMaintainDeptList_endWork();
        //查询
        return InspectionResp.getMap(deptCodes, RepEnum.REP_OK);
    }
    @GetMapping("/getMaintainEmpList")
    @ApiOperation(value = "获取要分派的工程师")
    @ApiImplicitParam(name = "deptCode", value = "科室", required = true)
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getMaintainEmpList(@RequestParam("empCode") String empCode) {
        List<EmpInfo> deptCodes = detailService.getMaintainEmpList(empCode);
        //查询
        return InspectionResp.getMap(deptCodes, RepEnum.REP_OK);
    }
    /**
     * 查看是否分派 的状态
     * @param
     * @return
     */
    @PostMapping("/getMaintainState")
    @ApiOperation(value = "查看流程(调度人员)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCode", value = "科室", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getMaintainState(@RequestParam("deptCode") String deptCode) {
        StatusDetail sd= detailService.getMaintainState(deptCode);
        UserDuty ud=new UserDuty();
        ud.setKey(sd.getCode()+"").setValue(sd.getMsg());
        //查询
        return InspectionResp.getMap(ud, RepEnum.REP_OK);
    }

    /**
     *
     * @param deptCodes
     * @param empCode
     * @return
     */
    @PostMapping("/saveMaintainEmpCode")
    @ApiOperation(value = "给科室分派工程师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCodes", value = "选择要保养的科室,数组", required = true),
            @ApiImplicitParam(name = "empCode", value = "选择的工程师", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> saveMaintainEmpCode(@RequestParam("deptCodes") List<String> deptCodes, @RequestParam("empCode") String empCode) {
        Iterator<String> iterator = deptCodes.iterator();
        while (iterator.hasNext()){
            String deptCode = iterator.next();
            detailService.saveMaintainEmpCode(deptCode, empCode);
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
     * @descrition 插入保养内容和方式
     */
    @PostMapping("/insertMaintainInfo")
    @ApiOperation(value = "插入保养内容和方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "equiArchNo", value = "资产卡号", required = true),
            @ApiImplicitParam(name = "maintainSelf", value = "保养内容", required = true),
            @ApiImplicitParam(name = "maintainWay", value = "保养方式", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> insertMaintainInfo(@RequestParam("equiArchNo") String equiArchNo,
                                                  @RequestParam("maintainSelf") String maintainSelf,
                                                  @RequestParam("maintainWay") String maintainWay,
                                                  @RequestParam("remarks") String remarks) {
//        detailService.insertMaintainInfo(equiArchNo, maintainSelf,maintainWay,remarks);
//是否需要先判断 状态
        UpdateWrapper<EquiMaintainDetailWork> queryWrapper = new UpdateWrapper<>();
        queryWrapper.lambda().eq(EquiMaintainDetailWork::getEquiArchNo, equiArchNo);
        EquiMaintainDetailWork equiMaintainDetailWork = new EquiMaintainDetailWork().setMaintainSelf(maintainSelf).
                setMaintainWay(maintainWay).setRemarks(remarks);
        detailService.update(equiMaintainDetailWork, queryWrapper);

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
