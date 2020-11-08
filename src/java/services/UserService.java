/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Users;

/**
 *
 * @author Group CCC
 */
public class UserService 
{

    public int update(String username, String password, String firstname, String lastname, String email) throws Exception {
        int rowUpdated = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        String preparedSQL = "UPDATE users SET "
                            + "   username = ?," 
                            + "   password = ?," 
                            + "   firstname = ?, "
                            + "   lastname = ?,"
                            + "   email = ?"
                            + "WHERE username = ?";
        
        PreparedStatement ps = connection.prepareStatement(preparedSQL);
        
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, firstname);
        ps.setString(4, lastname);
        ps.setString(5, email);
        ps.setString(6, username);
        
        rowUpdated = ps.executeUpdate();

        return rowUpdated;
    }
   
        
       public Users get(String username) throws Exception {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        
        String preparedSQL ="SELECT * " 
                   + "FROM users WHERE username = ?";

        
        PreparedStatement ps = connection.prepareStatement(preparedSQL);
        
        ps.setString(1, username);
        
        ResultSet p1 = ps.executeQuery();

          Users u1 = new Users(p1.getString("username"),p1.getString("password"),
                  p1.getString("firstname"), p1.getString("lastname"),p1.getString("email"));
          
          connection.close();

        return u1;
     }
    
    public List getAll() throws Exception{
        List<Users> allUsers = new ArrayList();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement ps = connection.createStatement();
        ResultSet userList = ps.executeQuery("SELECT * FROM users");
        while(userList.next()){
            allUsers.add(new Users(userList.getString("username"),userList.getString("password"),
                    userList.getString("firstname"),userList.getString("lastname"),userList.getString("email")));
        }
        pool.freeConnection(connection);
        userList.close();
        return allUsers;
    }
          
    public int insert(String username, String firstname, String lastname, String password, String email) throws Exception
    {
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
    String preparedQuery =
        "INSERT INTO users "
    + "(username, password, firstname, lastname,email) "
    + "VALUES "
    + "(?, ?, ?, ?, ?)";
    PreparedStatement ps = connection.prepareStatement(preparedQuery);
    ps.setString(1, username);
    ps.setString(2, password);
    ps.setString(3, firstname);
    ps.setString(4, lastname);
    ps.setString(5, email);
    int rows = ps.executeUpdate(); 
    pool.freeConnection(connection);
        return rows;
    }
    
        
    public int delete(String username) throws Exception{
        //get connection
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        //prepare Delete statement
        String deleteQuery = "DELETE FROM users "
                + "WHERE username = ?";
        PreparedStatement delete = connection.prepareStatement(deleteQuery);
        
        //set the value and excute
        delete.setString(0, username);
        int rows = delete.executeUpdate();
        
        //release the connection and retrun the number of rows
        pool.freeConnection(connection);
        return rows;
    }  
    
}
