package com.dao.impl;

import com.dao.SrvGroupJCDao;
import com.entities.SrvGroupJC;
import com.utils.DBUtil;
import com.utils.IniReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SrvGroupJCDaoImpl implements SrvGroupJCDao {

    @Override
    public List<SrvGroupJC> getSrvGroupJCBySrvGroupIDs(String srvGroupIds) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<SrvGroupJC> list = new ArrayList<>();
        String sql = "select * from view_srvGroup_JC where 1=1 ";
        if (!srvGroupIds.equals("-1")){
            sql = sql + " and srvGroup_Id in ( "+ srvGroupIds +" )";
        }
        try {
            conn = DBUtil.getConnection();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                SrvGroupJC srvGroupJC = new SrvGroupJC();
                srvGroupJC.setSrvGroupId(rs.getString("srvGroup_Id"));
                srvGroupJC.setSrvGroupName(rs.getString("srvGroup_Name"));
                srvGroupJC.setSrvGroupCode(rs.getString("srvGroup_Code"));
                srvGroupJC.setSrvGroupLetter(rs.getString("srvGroup_Letter"));
                list.add(srvGroupJC);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,pst,rs);
        }
        return null;
    }

}
