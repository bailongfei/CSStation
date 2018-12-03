package com.services;

import com.Info.ResultInfo;
import com.entities.CallResult;
import com.entities.CustomerQueueJCRecording;
import com.entities.SrvGroupJC;
import com.entities.UserInfo;

import java.util.List;

public interface SrvGroupJCService {

    //加载可选检查列表
    ResultInfo<List<SrvGroupJC>> getSrvGroupJC();

    //执行注册存储过程
    ResultInfo<CallResult> registerCallStatement(UserInfo userInfo,String customerType,SrvGroupJC srvGroupJC);

    //加载列表
    ResultInfo<List<CustomerQueueJCRecording>> getCustomerQueueList(String srvGroupID);

    //过号激活
    ResultInfo<Integer> restartCallStatement(CustomerQueueJCRecording customerQueueJCRecording);
}
