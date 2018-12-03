package com.dao;

import com.entities.SrvGroupJC;

import java.util.List;

public interface SrvGroupJCDao {
    //获取可检查列表
    List<SrvGroupJC> getSrvGroupJCBySrvGroupIDs(String srvGroupIds);

}
