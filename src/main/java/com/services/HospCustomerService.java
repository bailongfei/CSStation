package com.services;

import com.Info.ResultInfo;
import com.entities.UserInfo;

public interface HospCustomerService {

    ResultInfo<UserInfo> queryHospCustomer(String inHospNo);
}
