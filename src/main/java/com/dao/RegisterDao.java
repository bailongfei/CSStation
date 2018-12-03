package com.dao;

import com.entities.CallResult;
import com.entities.SrvGroupJC;
import com.entities.UserInfo;

public interface RegisterDao {

    CallResult registerCustomerBySrvGroupJC(UserInfo userInfo,String customerType, SrvGroupJC srvGroupJC);

    int  restartByDataId(String dataId);

}
