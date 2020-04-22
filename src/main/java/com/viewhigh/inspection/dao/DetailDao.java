package com.viewhigh.inspection.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viewhigh.inspection.entry.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhangS
 * @Description
 * @date 2019-11-14 15:20
 */
public interface DetailDao extends BaseMapper<EquiMaintainDetailWork> {
    List<String> getFileNames(String equiArchNo,String detailId);
    void saveFilePath(String equiArchNo,String detailId,String filePath);
    List<Map> getEquiCardNoGpDeptCode_endWork(String empCode);
    List<Map> getEquiCardNoGpDeptCode(String empCode,int isMaintain);
    List<EmpInfo> getMaintainEmpList(String empCode);
    //saveMaintainEmpCode saveFilePath
    void saveMaintainEmpCode(String deptCode,String empCode);
    List<EmpInfo> isHeadEmpCode(String empCode);
    List<EquiCardInfo> getEquiCardListByDeptCode(Map<String,String> map);
    List<EquiCardInfo> getEquiCardListByDeptCode2(Map<String,String> map);
    void recallDeptCode(String deptCode);
    void recallProject(String deptCode);
    List<Map> getProjectList(String empCode,int isMaintain);
    void orderProject(String deptCode);
    void endProject(Map<String,String> map);
    List<Map> getProjectHistoryList(String empCode);
    StatusDetail getMaintainState(String deptCode);
    List<Map> getMaintainContent(Map<String,String> map);
    void insertMaintainInfo(EquiMaintainInfo equiMaintainInfo);
    int getEquiWorkCardByIdAndEqNo(Map<String,String> map);
}
