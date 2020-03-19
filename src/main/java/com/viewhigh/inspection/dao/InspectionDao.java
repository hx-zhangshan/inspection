package com.viewhigh.inspection.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viewhigh.inspection.entry.Dept_info;
import com.viewhigh.inspection.entry.EmpInfo;
import com.viewhigh.inspection.entry.EquiCardInfo;
import com.viewhigh.inspection.entry.EquiInspectionDetailWork;

import java.util.List;
import java.util.Map;
/**
 * @author zhangS
 * @Description
 * @date 2019-12-11 10:14
 */
public interface InspectionDao extends BaseMapper<EquiInspectionDetailWork> {
    List<Dept_info> getEquiCardNoGpDeptCode();
    List<Dept_info> getEquiCardNoGpDeptCode_copy();
    List<EmpInfo> getInspectionEmpList();
    //saveInspectionEmpCode
    void saveInspectionEmpCode(String deptCode,String empCode);
    List<EmpInfo> isHeadEmpCode(String empCode);
    List<EquiCardInfo> getEquiCardListByDeptCode(Map<String,String> map);
    int recallDeptCode(String deptCode);
    int recallProject(String deptCode,String empCode);
    List<Dept_info> getProjectList(Map<String,String> map);
    void orderProject(String deptCode,String empCode);
    List<EquiCardInfo> getEquiCardListByDeptCode2(Map<String,String> map);
    void endProject(Map<String,String> map);
    List<Dept_info> getProjectHistoryList(String empCode);
}
