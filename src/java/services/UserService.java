/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.NotesDB;
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
        NotesDB ndb = new NotesDB();
        Users user = new Users(username, password, firstname, lastname, email);        
        
        ndb.update(user);
        rowUpdated++;
        
        return rowUpdated;
    }
   
        
       public Users get(String username) throws Exception {
        
       NotesDB ndb = new NotesDB();
       Users u1 = ndb.get(username);
       return u1;
     }
    
    public List getAll() throws Exception{
        List<Users> allUsers = new ArrayList();
        NotesDB ndb = new NotesDB();
        allUsers = ndb.getAll();
        
        return allUsers;
    }
          
    public int insert(String username, String firstname, String lastname, String password, String email) throws Exception
    {   
        int rows=0;
        NotesDB ndb = new NotesDB();
        Users user = new Users(username, password, firstname, lastname, email);
        ndb.insert(user);
        rows++;
        
        return rows;
    }
    
        
    public int delete(String username) throws Exception{
        int rows = 0;
        NotesDB ndb = new NotesDB();
        
        ndb.delete(username);
        rows++;
        
        return rows;
    }  
    
}
