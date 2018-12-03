package com.services.impl;

import com.Info.ResultInfo;
import com.dao.CustomerJCDao;
import com.dao.RegisterDao;
import com.dao.SrvGroupJCDao;
import com.dao.impl.CustomerJCDaoImpl;
import com.dao.impl.RegisterDaoImpl;
import com.dao.impl.SrvGroupJCDaoImpl;
import com.entities.CallResult;
import com.entities.CustomerQueueJCRecording;
import com.entities.SrvGroupJC;
import com.entities.UserInfo;
import com.services.SrvGroupJCService;
import com.utils.IniReader;
import com.utils.LogUtil;

import java.util.List;

public class SrvGroupKCServiceImpl implements SrvGroupJCService {

    private SrvGroupJCDao srvGroupJCDao = new SrvGroupJCDaoImpl();
    private RegisterDao registerDao = new RegisterDaoImpl();
    private CustomerJCDao customerJCDao = new CustomerJCDaoImpl();

    @Override
    public ResultInfo<List<SrvGroupJC>> getSrvGroupJC() {
        ResultInfo<List<SrvGroupJC>> resultInfo = new ResultInfo<>();
        resultInfo.setStatus(0);
        List<SrvGroupJC> list = srvGroupJCDao.getSrvGroupJCBySrvGroupIDs(IniReader.readIniGetSrvGroupId());
        if (list == null) {
            resultInfo.setInformation("执行查询过程中断，抛出异常。");
        } else {
            if (list.size()<1){
                resultInfo.setInformation("查询结果数量小于1。");
                resultInfo.setT(null);
            } else {
                resultInfo.setStatus(1);
                resultInfo.setInformation("查询成功。");
                resultInfo.setT(list);
            }
        }
        return resultInfo;
    }

    @Override
    public ResultInfo<CallResult> registerCallStatement(UserInfo userInfo,String customerType,SrvGroupJC srvGroupJC) {
        CallResult callResult = registerDao.registerCustomerBySrvGroupJC(userInfo,customerType,srvGroupJC);
        ResultInfo<CallResult> resultInfo = new ResultInfo<>();
        resultInfo.setStatus(0);
        if (callResult!=null){
            if (callResult.getRs().equals(0)){
                resultInfo.setInformation("执行存储过程失败。");
            } else {
                resultInfo.setStatus(1);
                resultInfo.setInformation("操作成功。");
                resultInfo.setT(callResult);
            }
        } else {
            resultInfo.setInformation("执行存储过程中断，抛出异常。");
        }
        LogUtil.markLog(resultInfo.getStatus(),userInfo.getId()+" "+userInfo.getUserName()+"进行登记操作"+resultInfo.getInformation());
        return resultInfo;
    }

    @Override
    public ResultInfo<List<CustomerQueueJCRecording>> getCustomerQueueList(String srvGroupID) {
        ResultInfo<List<CustomerQueueJCRecording>> resultInfo = new ResultInfo<>();
        resultInfo.setStatus(0);
        List<CustomerQueueJCRecording> list = customerJCDao.queryRegisterCustomerBySrvGroupJCId(srvGroupID);
        if (list!=null){
            resultInfo.setStatus(1);
            resultInfo.setT(list);
            resultInfo.setInformation("查询成功。");
        } else {
            resultInfo.setInformation("执行查询过程中断，抛出异常。");
        }
        return resultInfo;
    }

    @Override
    public ResultInfo<Integer> restartCallStatement(CustomerQueueJCRecording customerQueueJCRecording) {
        ResultInfo<Integer> resultInfo = new ResultInfo<>();
        resultInfo.setStatus(0);
        Integer rs = registerDao.restartByDataId(customerQueueJCRecording.getDataId());
        if (rs==1){
            resultInfo.setInformation("过号激活成功成功。");
            resultInfo.setT(rs);
            resultInfo.setStatus(1);
        } else {
            resultInfo.setInformation("执行查询过程中断，抛出异常。");
        }
        return resultInfo;
    }
}
