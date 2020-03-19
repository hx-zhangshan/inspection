package com.viewhigh.inspection.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.viewhigh.inspection.entry.Dept_info;
import com.viewhigh.inspection.entry.EmpInfo;
import com.viewhigh.inspection.entry.EquiCardInfo;
import com.viewhigh.inspection.entry.EquiInspectionDetailWork;

import java.util.List;

/**
 * @author zhangS
 * @Description
 * @date 2019-11-14 11:48
 */
public interface InspectionService extends IService<EquiInspectionDetailWork> {
    List<Dept_info> getInspectionDeptList(int isInspection);
    List<EmpInfo> getInspectionEmpList();
    void saveInspectionEmpCode(String deptCode, String empCode);
    List<EmpInfo> isHeadEmpCode(String empCode);
    List<EquiCardInfo> getEquiCardListByDeptCode(String deptCode, int state, int isUnusual);
    int recallDeptCode(String deptCode);
    int recallProject(String deptCode, String empCode);
    List<Dept_info> getProjectList(String empCode);
    void orderProject(String deptCode, String empCode);
    List<EquiCardInfo> getEquiCardListByDeptCode2(String deptCode, String empCode);
    void endProject(String deptCode, String empCode);
    List<Dept_info> getProjectHistoryList(String empCode);
}
