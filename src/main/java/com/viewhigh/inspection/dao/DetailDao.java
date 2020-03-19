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

    List<Dept_info> getEquiCardNoGpDeptCode_endWork();
    List<Dept_info> getEquiCardNoGpDeptCode();
    List<Dept_info> getEquiCardNoGpDeptCode_copy();
    List<EmpInfo> getMaintainEmpList(String empCode);
    //saveMaintainEmpCode
    void saveMaintainEmpCode(String deptCode,String empCode);
    List<EmpInfo> isHeadEmpCode(String empCode);
    List<EquiCardInfo> getEquiCardListByDeptCode(Map<String,String> map);
    int recallDeptCode(String deptCode);
    int recallProject(String deptCode,String empCode);
    List<Dept_info> getProjectList(Map<String,String> map);
    void orderProject(String deptCode,String empCode);
    List<EquiCardInfo> getEquiCardListByDeptCode2(Map<String,String> map);
    void endProject(Map<String,String> map);
    List<Dept_info> getProjectHistoryList(String empCode);
    StatusDetail getMaintainState(String deptCode);
}
