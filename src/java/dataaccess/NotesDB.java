/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
     
     public List<Users> getAll() throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
             List<Users> user = em.createNamedQuery("Users.findAll",Users.class).getResultList();
             return user;
         }finally{
             em.close();
         }
     }
     
     public void insert(Users user )throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
          trans.begin();
          em.persist(user);
          trans.commit();
        }catch(Exception ex){
          trans.rollback();
        }
        finally{
            em.close(); 
        }
     }
     public void update(Users user)throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
          trans.begin();
          em.merge(user);
          trans.commit();
        }catch(Exception ex){
          trans.rollback();
        }
        finally{
            em.close(); 
        }
     }   
     
    public void delete(String username) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
          Users user = em.find(Users.class, username);
          trans.begin();
          em.remove(em.merge(user));
          trans.commit();
        }catch(Exception ex){
          trans.rollback();
        }
        finally{
            em.close(); 
        } 
    }
     
}
