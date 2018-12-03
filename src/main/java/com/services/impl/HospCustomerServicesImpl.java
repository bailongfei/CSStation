package com.services.impl;

import com.Info.ResultInfo;
import com.dao.HospPatCustomerDao;
import com.dao.impl.HospPatCustomerDaoImpl;
import com.entities.UserInfo;
import com.services.HospCustomerService;

public class HospCustomerServicesImpl implements HospCustomerService {

    private HospPatCustomerDao hospPatDao = new HospPatCustomerDaoImpl();

    @Override
    public ResultInfo<UserInfo> queryHospCustomer(String inHospNo) {
        ResultInfo<UserInfo> resultInfo = new ResultInfo<>();
        resultInfo.setStatus(0);
        UserInfo userInfo = hospPatDao.queryHospCustomerInfo(inHospNo);
        if (userInfo!=null){
            resultInfo.setStatus(1);
            resultInfo.setInformation("查询成功，病人名为"+userInfo.getUserName());
            resultInfo.setT(userInfo);
        } else {
            resultInfo.setT(null);
            resultInfo.setInformation("HisDB1查询无结果");
        }
        return resultInfo;
    }
}
