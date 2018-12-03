package com.dao.impl;

import com.dao.RegisterDao;
import com.entities.CallResult;
import com.entities.SrvGroupJC;
import com.entities.UserInfo;
import com.utils.DBUtil;

import java.sql.*;

public class RegisterDaoImpl implements RegisterDao {

    @Override
    public CallResult registerCustomerBySrvGroupJC(UserInfo userInfo, String customerType,SrvGroupJC srvGroupJC) {
        String sql = "{call SP_DISTRIBUTE_TICKET_HSBY_JC2(?,?,?,?,?,sysdate,?,?,?,?)}";
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = DBUtil.getConnection();
            callStmt = conn.prepareCall(sql);
            callStmt.setString(1,userInfo.getId());
            callStmt.setString(2,userInfo.getUserName());
            callStmt.setInt(3,Integer.parseInt(customerType));
            callStmt.setString(4,srvGroupJC.getSrvGroupName());
            callStmt.setString(5,"门诊");
            callStmt.registerOutParameter(6,Types.INTEGER);
            callStmt.registerOutParameter(7,Types.NVARCHAR);
            callStmt.registerOutParameter(8,Types.INTEGER);
            callStmt.registerOutParameter(9,Types.INTEGER);
            callStmt.execute();
            CallResult callResult = new CallResult();
            callResult.setRs(callStmt.getInt(9));
            callResult.setWaitNum(callStmt.getInt(6));
            callResult.setSrvGroupLetter(callStmt.getString(7));
            callResult.setQueueNo(callStmt.getInt(8));
            return callResult;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,callStmt,null);
        }
        return null;
    }

    @Override
    public int restartByDataId(String dataId) {
        String sql = "{call SP_Queue_Restart_JC(?,?)}";
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = DBUtil.getConnection();
            callStmt = conn.prepareCall(sql);
            callStmt.setInt(1,Integer.parseInt(dataId));
            callStmt.registerOutParameter(2,Types.INTEGER);
            callStmt.execute();
            return callStmt.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,callStmt,null);
        }
        return 0;
    }
}
