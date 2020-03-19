package com.viewhigh.inspection.serviceImp;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viewhigh.inspection.dao.InspectionDao;
import com.viewhigh.inspection.entry.Dept_info;
import com.viewhigh.inspection.entry.EmpInfo;
import com.viewhigh.inspection.entry.EquiCardInfo;
import com.viewhigh.inspection.entry.EquiInspectionDetailWork;
import com.viewhigh.inspection.service.InspectionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangS
 * @Description
 * @date 2019-12-11 10:11
 */
@Service
public class InspectionServiceImp extends ServiceImpl<InspectionDao, EquiInspectionDetailWork> implements InspectionService {

    @Override
    public List<Dept_info> getInspectionDeptList(int isInspection) {
        if (isInspection == 0) {
            return this.baseMapper.getEquiCardNoGpDeptCode();
        }
        return this.baseMapper.getEquiCardNoGpDeptCode_copy();
    }

    @Override
    public List<EmpInfo> getInspectionEmpList() {
        return this.baseMapper.getInspectionEmpList();
    }

    @Override
    public void saveInspectionEmpCode(String deptCode, String empCode) {

        this.baseMapper.saveInspectionEmpCode(deptCode, empCode);
    }

    @Override
    public List<EmpInfo> isHeadEmpCode(String empCode) {
        return this.baseMapper.isHeadEmpCode(empCode);
    }

    @Override
    public List<EquiCardInfo> getEquiCardListByDeptCode(String deptCode, int state, int isUnusual) {
        Map<String, String> map = new HashMap<>();
        map.put("deptCode", deptCode);
        map.put("state", state + "");
        map.put("isUnusual", isUnusual + "");
        return this.baseMapper.getEquiCardListByDeptCode(map);
    }

    @Override
    public int recallDeptCode(String deptCode) {

        return this.baseMapper.recallDeptCode(deptCode);
    }

    @Override
    public List<Dept_info> getProjectList(String empCode) {
        Map<String, String> map = new HashMap<>();
        if (!StringUtils.isEmpty(empCode)) {
            map.put("empCode", empCode);
        }

        return this.baseMapper.getProjectList(map);
    }

    @Override
    public void orderProject(String deptCode, String empCode) {
        this.baseMapper.orderProject(deptCode, empCode);
    }

    @Override
    public int recallProject(String deptCode, String empCode) {

        return this.baseMapper.recallProject(deptCode, empCode);
    }

    @Override
    public List<EquiCardInfo> getEquiCardListByDeptCode2(String deptCode, String empCode) {
        Map<String, String> map = new HashMap<>();
        map.put("deptCode", deptCode);
        map.put("empCode", empCode);
        return this.baseMapper.getEquiCardListByDeptCode2(map);
    }

    @Override
    public void endProject(String deptCode, String empCode) {
        Map<String, String> map = new HashMap<>();
        map.put("deptCode", deptCode);
        map.put("empCode", empCode);
        this.baseMapper.endProject(map);
    }

    @Override
    public List<Dept_info> getProjectHistoryList(String empCode) {
        return this.baseMapper.getProjectHistoryList(empCode);
    }

}

