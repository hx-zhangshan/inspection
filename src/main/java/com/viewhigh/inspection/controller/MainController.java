package com.viewhigh.inspection.controller;

import com.viewhigh.inspection.bean.InspectionResp;
import com.viewhigh.inspection.bean.RepEnum;
import com.viewhigh.inspection.bean.UserDuty;
import com.viewhigh.inspection.entry.EmpInfo;
import com.viewhigh.inspection.entry.EquiCardInfo;
import com.viewhigh.inspection.entry.EquiMaintainInfo;
import com.viewhigh.inspection.entry.StatusDetail;
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
     * 文件加载  从equi_inspection_file_path 中 返回文件名字
     * @param
     * @return
     */
    @PostMapping("/getFileNames")
    @ApiOperation(value = "获取文件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "detailId", value = "附表中的主键", required = true),
            @ApiImplicitParam(name = "equiArchNo", value = "资产卡号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getFileNames( @RequestParam("equiArchNo") String equiArchNo,
                                                   @RequestParam("detailId") String detailId ) {
        List<String> fileNames = detailService.getFileNames(equiArchNo, detailId);
        return InspectionResp.getMap(fileNames, RepEnum.REP_OK);
    }
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    @ApiOperation(value = "文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "detailId", value = "附表中的主键", required = true),
            @ApiImplicitParam(name = "equiArchNo", value = "资产卡号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> handleFileUpload_T( MultipartFile[] file,@RequestParam("equiArchNo") String equiArchNo,
                                                   @RequestParam("detailId") String detailId ) throws IOException {
        //存文件 到系统 文件夹下 upload-dir
        for (MultipartFile fileInfo:file){
            String name = storageService.store(fileInfo);
            detailService.saveFilePath(equiArchNo,detailId,name);
        }

        //生成 特定的文件名 和路径存在数据库
//        UpdateWrapper<EquiMaintainDetailWork> queryWrapper = new UpdateWrapper<>();
//        queryWrapper.lambda().eq(EquiMaintainDetailWork::getEquiArchNo, equiArchNo);
//        EquiMaintainDetailWork equiMaintainDetailWork = new EquiMaintainDetailWork().setFilePath(names.toString());
//        detailService.saveFilePath(equiArchNo,detailId,names.toString());
        return InspectionResp.getMap(null, RepEnum.REP_OK);
    }

    @GetMapping("/getMaintainDeptList/{isMaintain}")
    @ApiOperation(value = "调度中心—获取保养列表通过流水号分组 0 未保养 其他已经保养")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isMaintain", value = "查询isMaintain", required = true),
            @ApiImplicitParam(name = "empCode", value = "工号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getMaintainDeptList(@PathVariable("isMaintain") int isMaintain,
                                                   @RequestParam("empCode")String empCode) {
        List<Map> maintainDeptList = detailService.getMaintainDeptList(isMaintain, empCode);
        //查询
        return InspectionResp.getMap(maintainDeptList, RepEnum.REP_OK);
    }

    /**
     * 查看已经完工状态的 历史单子
     * @return
     */
    @GetMapping("/getMaintainDeptList_endWork")
    @ApiOperation(value = "调度中心历史-获取已完工保养列表 ")
    @ApiImplicitParam(name = "empCode", value = "工号", required = true)
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getMaintainDeptList_endWork(String empCode) {
        List<Map> endWorks = detailService.getMaintainDeptList_endWork(empCode);
        //查询
        return InspectionResp.getMap(endWorks, RepEnum.REP_OK);
    }

    /**
     * 这里获取分派工程师 归口部门，属性为工程师的
     * @param empCode
     * @return
     */
    @GetMapping("/getMaintainEmpList")
    @ApiOperation(value = "获取要分派的工程师")
    @ApiImplicitParam(name = "empCode", value = "工号", required = true)
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
            @ApiImplicitParam(name = "idNo", value = "流水号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getMaintainState(@RequestParam("idNo") String idNo) {
        StatusDetail sd= detailService.getMaintainState(idNo);
        UserDuty ud=new UserDuty();
        ud.setKey(sd.getCode()+"").setValue(sd.getMsg());
        //查询
        return InspectionResp.getMap(ud, RepEnum.REP_OK);
    }

    /**
     *
     * @param idNos
     * @param empCode
     * @return
     */
    @PostMapping("/saveMaintainEmpCode")
    @ApiOperation(value = "给项目分派工程师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idNos", value = "选择要保养的流水号,数组", required = true),
            @ApiImplicitParam(name = "empCode", value = "选择的工程师", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> saveMaintainEmpCode(@RequestParam("idNos") List<String> idNos, @RequestParam("empCode") String empCode) {
        Iterator<String> iterator = idNos.iterator();
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
            @ApiImplicitParam(name = "idNo", value = "流水号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getEquiCardListByDeptCode(@RequestParam("idNo") String idNo) {
        List<EquiCardInfo> empInfos = detailService.getEquiCardListByDeptCode(idNo);
        //查询
        return InspectionResp.getMap(empInfos, RepEnum.REP_OK);
    }

    @PostMapping("/recallDeptCode")
    @ApiOperation(value = "(调度中心)撤回已处理项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idNos", value = "流水号,数组", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    @Transactional(rollbackFor = CommonException.class)
    public Map<String, Object> recallDeptCode(@RequestParam("idNos") List<String> idNos) {
        Iterator<String> iterator = idNos.iterator();
        while (iterator.hasNext()) {
            String deptCode = iterator.next();
            detailService.recallDeptCode(deptCode);
        }
        //查询
        return  InspectionResp.getMap(null, RepEnum.REP_OK);
    }
    @GetMapping("/getProjectList/{isMaintain}")
    @ApiOperation(value = "工程师—获取工单列表通过流水号分组 0 新任务 其他处理中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isMaintain", value = "查询isMaintain", required = true),
            @ApiImplicitParam(name = "empCode", value = "工号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getProjectList(@PathVariable("isMaintain") int isMaintain,
                                                   @RequestParam("empCode")String empCode) {
        List<Map> maintainDeptList = detailService.getProjectList(isMaintain, empCode);
        //查询
        return InspectionResp.getMap(maintainDeptList, RepEnum.REP_OK);
    }


    @PostMapping("/orderProject")
    @ApiOperation(value = "工程师接单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idNo", value = "流水号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> orderProject(@RequestParam("idNo") String idNo) {
        detailService.orderProject(idNo);
        log.info(":工程师接单成功！");
        //查询
        return InspectionResp.getMap(null, RepEnum.REP_OK);
    }

    @PostMapping("/recallProject")
    @ApiOperation(value = "工程师退回")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idNo", value = "流水号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> recallProject(@RequestParam("idNo") String idNo) {
         detailService.recallProject(idNo);
         log.info(":工程师接单退回！");
        //查询
        return InspectionResp.getMap(null, RepEnum.REP_OK);
    }

    /**
     * @param idNo
     * @return
     * @descrition 工程师只能查询 分派给她自己的 资产
     */
    @PostMapping("/getEquiCardListByDeptCode2")
    @ApiOperation(value = "工程师查询自己资产")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idNo", value = "流水号", required = true),
            @ApiImplicitParam(name = "isDo", value = "是否处理", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getEquiCardListByDeptCode2( @RequestParam("idNo") String idNo,@RequestParam("isDo") String isDo) {
        List<EquiCardInfo> empInfos = detailService.getEquiCardListByDeptCode2( idNo,isDo);
        //查询
        return InspectionResp.getMap(empInfos, RepEnum.REP_OK);
    }

    /**
     * @param
     * @return
     * @descrition 插入保养内容和方式
     */
    @PostMapping("/insertMaintainInfo")
    @ApiOperation(value = "插入保养内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "equiMaintainInfo", value = "资产信息",
                    required = true,example ="{\n" +
                    "  \"maintainDatas\": [\n" +
                    "  \n" +
                    "    {\n" +
                    "      \"infoData\": \"\",\n" +
                    "      \"infoName\": \"语音路径\",\n" +
                    "      \"infoCode\": \"DDDD\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"equiArchNo\": \"成功\",\n" +
                    "  \"detailId\": \"\",\n" +
                    "\"xmCode\":\"\"\n" +
                    "}" )
    })
    @ApiResponse(code = 200, response = EquiMaintainInfo.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> insertMaintainInfo( @RequestBody EquiMaintainInfo equiMaintainInfo) {
        detailService.insertMaintainInfo(equiMaintainInfo);
        log.info("JOSN::::"+equiMaintainInfo.toString());

        //查询
        return InspectionResp.getMap(null, RepEnum.REP_OK);
    }
    /**
     * @param
     * @return
     * @descrition 插入保养内容和方式
     */
    @PostMapping("/viewMaintainInfo")
    @ApiOperation(value = "查询保养内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "equiArchNo", value = "资产卡号", required = true),
            @ApiImplicitParam(name = "detailId", value = "主表id", required = true),
            @ApiImplicitParam(name = "xmCode", value = "项目编码", required = true),
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> viewMaintainInfo(@RequestParam("equiArchNo") String equiArchNo,
                                                  @RequestParam("detailId") String detailId,
                                                @RequestParam("xmCode") String xmCode) {

        List<Map> maps = detailService.getMaintainContent(equiArchNo, detailId, xmCode);

        //查询
        return InspectionResp.getMap(maps, RepEnum.REP_OK);
    }
    /**
     * @param idNos
     * @return
     * @descrition 工程师完工
     */
    @PostMapping("/endProject")
    @ApiOperation(value = "工程师完工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idNos", value = "流水号 数组", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> endProject(@RequestParam("idNos") List<String> idNos) {
        Iterator<String> iterator = idNos.iterator();
        while (iterator.hasNext()){
             String next = iterator.next();
            detailService.endProject(next);
        }
        log.info("项目完工！");
        //查询
        return InspectionResp.getMap(null, RepEnum.REP_OK);
    }
    /**
     * @param empCode
     * @return
     * @descrition 工程师 - 历史列表
     */
    @GetMapping("/getProjectHistoryList")
    @ApiOperation(value = "工程师-历史列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empCode", value = "工号", required = true)
    })
    @ApiResponse(code = 200, response = InspectionResp.class, message = "固定返回模型，json字符串表现形式；")
    public Map<String, Object> getProjectHistoryList( @RequestParam("empCode") String empCode) {
        List<Map> historyList = detailService.getProjectHistoryList(empCode);
        //查询
        return InspectionResp.getMap(historyList, RepEnum.REP_OK);
    }
}
