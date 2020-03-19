package com.viewhigh.inspection.serviceImp;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viewhigh.inspection.dao.DetailDao;
import com.viewhigh.inspection.entry.*;
import com.viewhigh.inspection.service.IDetailService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zhangS
 * @Description 服务实现类
 * @date 2019-11-14 11:49
 */
@Service
public class DetailServiceImpl extends ServiceImpl<DetailDao, EquiMaintainDetailWork> implements IDetailService {

    @Override
    public List<Dept_info> getMaintainDeptList(int isMaintain) {
        if (isMaintain == 0) {
            return this.baseMapper.getEquiCardNoGpDeptCode();
        }
        return this.baseMapper.getEquiCardNoGpDeptCode_copy();
    }

    @Override
    public List<EmpInfo> getMaintainEmpList(String empCode) {
        return this.baseMapper.getMaintainEmpList(empCode);
    }

    @Override
    public void saveMaintainEmpCode(String deptCode, String empCode) {

        this.baseMapper.saveMaintainEmpCode(deptCode, empCode);
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
    public List<Dept_info> getProjectList(String empCode){
        Map<String, String> map = new HashMap<>();
        if(!StringUtils.isEmpty(empCode)){
            map.put("empCode", empCode);
        }

        return this.baseMapper.getProjectList(map);
    }
    @Override
    public void orderProject(String deptCode,String empCode){
        this.baseMapper.orderProject(deptCode,empCode);
    }
    @Override
    public int recallProject(String deptCode,String empCode) {

        return this.baseMapper.recallProject(deptCode,empCode);
    }
    @Override
    public List<EquiCardInfo> getEquiCardListByDeptCode2(String deptCode, String empCode) {
        Map<String, String> map = new HashMap<>();
        map.put("deptCode", deptCode);
        map.put("empCode", empCode);
        return this.baseMapper.getEquiCardListByDeptCode2(map);
    }
    @Override
    public void endProject(String deptCode, String empCode){
        Map<String, String> map = new HashMap<>();
        map.put("deptCode", deptCode);
        map.put("empCode", empCode);
        this.baseMapper.endProject(map);
    }
    @Override
    public List<Dept_info> getProjectHistoryList(String empCode){
        return this.baseMapper.getProjectHistoryList(empCode);
    }
    @Override
    public StatusDetail getMaintainState(String deptCode){
        return this.baseMapper.getMaintainState(deptCode);
    }

    @Override
    public List<Dept_info> getMaintainDeptList_endWork(){
        return this.baseMapper.getEquiCardNoGpDeptCode_endWork();
    }
}
