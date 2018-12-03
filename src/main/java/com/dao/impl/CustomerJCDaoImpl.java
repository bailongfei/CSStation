package com.dao.impl;

import com.dao.CustomerJCDao;
import com.entities.CustomerQueueJCRecording;
import com.entities.UserInfo;
import com.utils.DBUtil;
import com.utils.HospDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerJCDaoImpl implements CustomerJCDao {

    @Override
    public List<CustomerQueueJCRecording> queryRegisterCustomerBySrvGroupJCId(String srvGroupName) {
        String sql ="select * from view_queue_all_jc where 1=1 " +
                " and RegDate > to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-mm-dd') " +
                " and SrvGroup_Name = ? order by status_type,regDate";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CustomerQueueJCRecording> customerQueueJCRecordingList = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1,srvGroupName);
            rs = pst.executeQuery();
            while (rs.next()){
                CustomerQueueJCRecording recording = new CustomerQueueJCRecording();
                recording.setDataId(""+rs.getInt("data_id"));
                recording.setQueueNo(""+rs.getInt("queue_No"));
                recording.setSrvGroupId(""+rs.getString("srvGroup_id"));
                recording.setSrvGroupName(""+rs.getString("srvGroup_Name"));
                recording.setCustomerName(""+rs.getString("customer_Name"));
                recording.setCustomerId(""+rs.getString("customer_Id"));
                recording.setStatusType(""+rs.getInt("status_Type"));
                recording.setSrvGroupLetter(""+rs.getString("srvGroup_Letter"));
                recording.setSrvCodeId(""+rs.getInt("srvCode_id"));
                recording.setSrvCodeName(""+rs.getString("srvCode_Name"));
                recording.setCustomerType(""+rs.getInt("customer_Type"));
                customerQueueJCRecordingList.add(recording);
            }
            return customerQueueJCRecordingList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pst,rs);
        }
        return null;
    }

}
