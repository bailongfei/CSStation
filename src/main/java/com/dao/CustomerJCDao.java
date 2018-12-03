package com.dao;

import com.entities.CustomerQueueJCRecording;

import java.util.List;

public interface CustomerJCDao {

    List<CustomerQueueJCRecording> queryRegisterCustomerBySrvGroupJCId(String SrvGroupName);



}