package com.example.stadionjsp_modul8egzamen.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {
    public static EntityManagerFactory emf=null;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
         emf = Persistence.createEntityManagerFactory("default");
    }
}
