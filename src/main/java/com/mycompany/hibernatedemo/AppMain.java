/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernatedemo;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author dhruv
 */
public class AppMain {

    private static Scanner sc;
    private static EntityManagerFactory factory;
    private static EntityManager em;
    private static final String PERSISTENCE_UNIT_NAME = "com.mycompany_HibernateDemo_jar_1.0-SNAPSHOTPU";

    public AppMain() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        sc = new Scanner(System.in);
    }

    public void retriveData() {
        TypedQuery<HelloWorld> q = em.createQuery("SELECT C FROM HelloWorld C", HelloWorld.class);
        List<HelloWorld> results = q.getResultList();

        results.forEach((u) -> {
            System.out.println(u);
        });
    }

    public void storeData() {
        HelloWorld u = new HelloWorld();
        System.out.print("Enter message:");
        String msg = sc.next();
        u.setMessage(msg);
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        System.out.println("Message is added!!");
    }

    public static void main(String[] args) {
        AppMain appMain = new AppMain();
        System.out.println("1: Retrieve data");
        System.out.println("2: Store data");
        try {
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    appMain.retriveData();
                    break;
                case 2:
                    appMain.storeData();

            }

        } finally {
            em.close();
        }
    }

}
