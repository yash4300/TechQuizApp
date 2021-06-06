/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquizapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import techquizapp.dbutil.DBConnection;
import techquizapp.pojo.User;

/**
 *
 * @author HP
 */
public class UserDAO {
    
    public static boolean validateUser(User user)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="Select * from Users where userid=? and password=? and usertype=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1,user.getUserId());
        ps.setString(2,user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs=ps.executeQuery();
        return rs.next();
        
    }

    public static boolean registerStudent(User user)throws SQLException
    {
        Connection conn = DBConnection.getConnection();
        String sql = "insert into users values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUserId());
        ps.setString(2, user.getPassword());
        ps.setString(3, "Student");
        int ans = ps.executeUpdate();
        if(ans == 1){
            return true;
        }
        else 
            return false;
    }
    
    public static boolean changePassword(User user)throws SQLException
    {
        Connection conn = DBConnection.getConnection();
        String sql = "update users set password = ? where userid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getPassword());
        ps.setString(2, user.getUserId());
        int result = ps.executeUpdate();
        if(result == 1){
            return true;
        }
        else
        {
            return false;
        }
    }
}
