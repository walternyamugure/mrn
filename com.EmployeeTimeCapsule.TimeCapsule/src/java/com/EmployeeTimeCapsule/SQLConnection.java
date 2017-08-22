package com.EmployeeTimeCapsule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class SQLConnection {

    public static final String url = "jdbc:sqlserver://localhost:1433;databaseName=EmployeeTimeCapsule;user=walter;password=pass123;";
    public static Connection connection = null;
    public static CallableStatement proc = null;
    public static String x = "0";

    public static boolean Connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }

    }

    public static ResultSet ExecuteQuery(PreparedStatement sql) {
        try {
            return sql.executeQuery();
        } catch (SQLException ex) {
            return null;
        }

    }

    public static String GetOneColTwoPara(String procname, String col1, String para1, String para2) throws SQLException {
        String result = "";
        SQLConnection.Connect();

        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);

        ResultSet r = SQLConnection.ExecuteQuery(s);
        while (r.next()) {
            result = r.getString(col1);
        }
        return result;
    }

    public static ResultSet GetFourColOnePara(String procname, String col1, String col2, String col3, String col4, String para1) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?)}");
        s.setString(1, para1);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet GetOnePara(String procname, String para1) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?)}");
        s.setString(1, para1);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet GetTwoPara(String procname, String para1, String para2) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet GetThreePara(String procname, String para1, String para2, String para3) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        s.setString(3, para3);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet GetFiveColTwoPara(String procname, String col1, String col2, String col3, String col4, String col5, String para1, String para2) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet GetEightColTwoPara(String procname, String col1, String col2, String col3, String col4, String col5, String col6, String col7, String col8, String para1, String para2) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet GetZeroPara(String procname) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "}");
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet UpdateTwoPara(String procname, String para1, String para2) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet UpdateThreePara(String procname, String para1, String para2, String para3) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        s.setString(3, para3);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet UpdateFourPara(String procname, String para1, String para2, String para3, String para4) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?,?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        s.setString(3, para3);
        s.setString(4, para4);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet UpdateFivePara(String procname, String para1, String para2, String para3, String para4, String para5) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?,?,?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        s.setString(3, para3);
        s.setString(4, para4);// para4); 
        s.setString(5, para5);//para5); 
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet UpdateSevenPara(String procname, String para1, String para2, String para3, String para4, String para5, String para6, String para7) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?,?,?,?,?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        s.setString(3, para3);
        s.setString(4, para4);
        s.setString(5, para5);
        s.setString(6, para6);
        s.setString(7, para7);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet UpdateEightPara(String procname, String para1, String para2, String para3, String para4, String para5, String para6, String para7, String para8) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?,?,?,?,?,?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        s.setString(3, para3);
        s.setString(4, para4);
        s.setString(5, para5);
        s.setString(6, para6);
        s.setString(7, para7);
        s.setString(8, para8);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet UpdateNinePara(String procname, String para1, String para2, String para3, String para4, String para5, String para6, String para7, String para8, String para9) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?,?,?,?,?,?,?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        s.setString(3, para3);
        s.setString(4, para4);
        s.setString(5, para5);
        s.setString(6, para6);
        s.setString(7, para7);
        s.setString(8, para8);
        s.setString(9, para9);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet UpdateTenPara(String procname, String para1, String para2, String para3, String para4, String para5, String para6, String para7, String para8, String para9, String para10) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?,?,?,?,?,?,?,?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        s.setString(3, para3);
        s.setString(4, para4);
        s.setString(5, para5);
        s.setString(6, para6);
        s.setString(7, para7);
        s.setString(8, para8);
        s.setString(9, para9);
        s.setString(10, para10);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet UpdateEightPara(String procname, String para1, String para2, int para3, int para4, int para5, int para6, String[] para) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        s.setInt(3, para3);
        s.setInt(4, para4);
        s.setInt(5, para5);
        s.setInt(6, para6);
        s.setString(7, para[0]);
        s.setString(8, para[1]);
        s.setString(9, para[2]);
        s.setString(10, para[3]);
        s.setString(11, para[4]);
        s.setString(12, para[5]);
        s.setString(13, para[6]);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }

    public static ResultSet UpdateTwentyFivePara(String procname, String para1, String para2, int para3, int para4, String[] prjct, String[] tsk, String[] hrs) throws SQLException {
        SQLConnection.Connect();
        PreparedStatement s = SQLConnection.connection.prepareCall("{call " + procname + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        s.setString(1, para1);
        s.setString(2, para2);
        s.setInt(3, para3);
        s.setInt(4, para4);

        s.setString(5, prjct[0]);
        s.setString(6, prjct[1]);
        s.setString(7, prjct[2]);
        s.setString(8, prjct[3]);
        s.setString(9, prjct[4]);
        s.setString(10, prjct[5]);
        s.setString(11, prjct[6]);

        s.setString(12, tsk[0]);
        s.setString(13, tsk[1]);
        s.setString(14, tsk[2]);
        s.setString(15, tsk[3]);
        s.setString(16, tsk[4]);
        s.setString(17, tsk[5]);
        s.setString(18, tsk[6]);

        s.setString(19, hrs[0]);
        s.setString(20, hrs[1]);
        s.setString(21, hrs[2]);
        s.setString(22, hrs[3]);
        s.setString(23, hrs[4]);
        s.setString(24, hrs[5]);
        s.setString(25, hrs[6]);
        ResultSet r = SQLConnection.ExecuteQuery(s);
        return r;
    }
}
