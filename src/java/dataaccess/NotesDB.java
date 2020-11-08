/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import javax.persistence.EntityManager;
import models.Users;

/**
 *
 * @author Chris
 */
public class NotesDB {
     public Users get(String username) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
             Users user = em.find(Users.class, username);
             return user;
         }finally{
             em.close();
         }
         
     }
}
