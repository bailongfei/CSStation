package com.dao.impl;

import com.dao.HospPatCustomerDao;
import com.entities.UserInfo;
import com.utils.DBUtil;
import com.utils.HospDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HospPatCustomerDaoImpl implements HospPatCustomerDao {

    @Override
    public UserInfo queryHospCustomerInfo(String inHospNo) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from V_XH_basicInfo_ZY where inHospNo = ?";
        try {
            conn = HospDBUtil.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1,inHospNo);
            rs = pst.executeQuery();
            UserInfo userInfo = null;
            while (rs.next()){
                userInfo = new UserInfo();
                userInfo.setId(rs.getString("MedCard"));
                userInfo.setUserName(rs.getString("PatName"));
            }
            return userInfo;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pst,rs);
        }
        return null;
    }
}
