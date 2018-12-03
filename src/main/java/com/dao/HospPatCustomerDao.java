package com.dao;

import com.entities.UserInfo;

public interface HospPatCustomerDao {

    UserInfo queryHospCustomerInfo(String InHospNo);

}
