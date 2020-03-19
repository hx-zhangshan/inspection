package com.viewhigh.inspection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.viewhigh.inspection.entry.*;

import java.util.List;

/**
 * @author zhangS
 * @Description
 * @date 2019-11-14 11:48
 */
public interface IDetailService extends IService<EquiMaintainDetailWork> {
    List<Dept_info> getMaintainDeptList(int isMaintain);
    List<EmpInfo> getMaintainEmpList(String empCode);
    void saveMaintainEmpCode(String deptCode,String empCode);
    List<EmpInfo> isHeadEmpCode(String empCode);
    List<EquiCardInfo> getEquiCardListByDeptCode(String deptCode, int state, int isUnusual);
    int recallDeptCode(String deptCode);
    int recallProject(String deptCode,String empCode);
    List<Dept_info> getProjectList(String empCode);
    void orderProject(String deptCode,String empCode);
    List<EquiCardInfo> getEquiCardListByDeptCode2(String deptCode, String empCode);
    void endProject(String deptCode,String empCode);
    List<Dept_info> getProjectHistoryList(String empCode);
    //getMaintainState
    StatusDetail getMaintainState(String deptCode);
    List<Dept_info> getMaintainDeptList_endWork();
}
