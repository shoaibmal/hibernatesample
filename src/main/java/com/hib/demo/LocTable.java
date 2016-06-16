package com.hib.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hib.entities.LocationTable;
import com.hib.init.HibernateUtil;
  
public class LocTable {  
  
    public static void main(String[] args) {  
  
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  
        Session session = sessionFactory.openSession();  
        session.beginTransaction();  
          
        LocationTable locTable = new LocationTable();
        locTable.setClientName("Shoaib");
        
       System.out.println(((Integer)session.save(locTable)));  
        session.getTransaction().commit();  
          
        session.close();  
  
    }  
}  