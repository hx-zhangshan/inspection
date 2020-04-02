package com.viewhigh.inspection.serviceImp;

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
    public List<Map> getMaintainDeptList(int isMaintain, String empCode) {
            //未处理  先通过 1，登陆人获取到 2，归口部门
            return this.baseMapper.getEquiCardNoGpDeptCode(empCode,isMaintain);

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
    public List<EquiCardInfo> getEquiCardListByDeptCode(String deptCode) {
        Map<String, String> map = new HashMap<>();
        map.put("deptCode", deptCode);
        return this.baseMapper.getEquiCardListByDeptCode(map);
    }

    @Override
    public void recallDeptCode(String deptCode) {

        this.baseMapper.recallDeptCode(deptCode);
    }
    @Override
    public List<Map> getProjectList(int isMaintain, String empCode){

        return this.baseMapper.getProjectList(empCode,isMaintain);
    }
    @Override
    public void orderProject(String deptCode){
        this.baseMapper.orderProject(deptCode);
    }
    @Override
    public void recallProject(String deptCode) {

        this.baseMapper.recallProject(deptCode);
    }
    @Override
    public List<EquiCardInfo> getEquiCardListByDeptCode2( String idNo,String isDo) {
        Map<String, String> map = new HashMap<>();
        map.put("idNo", idNo);
        map.put("isDo", isDo);
        return this.baseMapper.getEquiCardListByDeptCode2(map);
    }
    @Override
    public void endProject(String deptCode){
        Map<String, String> map = new HashMap<>();
        map.put("idNo", deptCode);
        this.baseMapper.endProject(map);
    }
    @Override
    public List<Map> getProjectHistoryList(String empCode){
        return this.baseMapper.getProjectHistoryList(empCode);
    }
    @Override
    public StatusDetail getMaintainState(String deptCode){
        return this.baseMapper.getMaintainState(deptCode);
    }

    @Override
    public List<Map> getMaintainDeptList_endWork(String empCode){
        return this.baseMapper.getEquiCardNoGpDeptCode_endWork(empCode);
    }
    @Override
    public List<Map> getMaintainContent(String equiArchNo,String detailId,String xmCode){
        Map<String, String> map = new HashMap<>();
        map.put("equiArchNo", equiArchNo);
        map.put("detailId", detailId);
        map.put("xmCode", xmCode);
        return this.baseMapper.getMaintainContent(map);
    }
    @Override
    public void insertMaintainInfo(EquiMaintainInfo equiMaintainInfo){
        //先查询是否有值 在equi_inspection_work_card中 无插入 有更新
//        Map<String, String> map = new HashMap<>();
//        map.put("equiArchNo", equiMaintainInfo.getEquiArchNo());
//        map.put("detailId", equiMaintainInfo.getDetailId());
//        int haveOne=this.baseMapper.getEquiWorkCardByIdAndEqNo(map);
        this.baseMapper.insertMaintainInfo(equiMaintainInfo);

    }
}
