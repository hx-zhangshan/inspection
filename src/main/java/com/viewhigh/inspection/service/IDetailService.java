package com.viewhigh.inspection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.viewhigh.inspection.entry.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhangS
 * @Description
 * @date 2019-11-14 11:48
 */
public interface IDetailService extends IService<EquiMaintainDetailWork> {
    List<String> getFileNames(String equiArchNo,String detailId);

    void saveFilePath(String equiArchNo,String detailId,String filePath);
    List<Map> getMaintainDeptList(int isMaintain, String empCode);
    List<EmpInfo> getMaintainEmpList(String empCode);
    void saveMaintainEmpCode(String deptCode,String empCode);
    List<EmpInfo> isHeadEmpCode(String empCode);
    List<EquiCardInfo> getEquiCardListByDeptCode(String deptCode);
    void recallDeptCode(String deptCode);
    void recallProject(String deptCode);
    List<Map> getProjectList(int isMaintain, String empCode);
    void orderProject(String deptCode);
    List<EquiCardInfo> getEquiCardListByDeptCode2( String idNo,String isDo);
    void endProject(String deptCode);
    List<Map> getProjectHistoryList(String empCode);
    //getMaintainState
    StatusDetail getMaintainState(String deptCode);
    List<Map> getMaintainDeptList_endWork(String empCode);
    List<Map> getMaintainContent(String equiArchNo,String detailId,String xmCode);
    void insertMaintainInfo(EquiMaintainInfo equiMaintainInfo);
}
