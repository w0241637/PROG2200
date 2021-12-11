package org.lwjglb.game;

import org.lwjglb.engine.items.Car;
import org.lwjglb.engine.items.Vehicle02;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Database helper class
 */
public class ManageJDO01 {

    Properties properties;
    PersistenceManagerFactory pmf;
    PersistenceManager pm;

    /**
     * open a connection to database
     * @param db_name
     */
    public ManageJDO01(String db_name) {
        try {
            properties = new Properties();
            properties.setProperty("javax.jdo.PersistenceManagerFactoryClass", "com.objectdb.jdo.PMF");
            properties.setProperty("javax.jdo.option.ConnectionURL", db_name);
            pmf = JDOHelper.getPersistenceManagerFactory(properties);
            pm = pmf.getPersistenceManager();
        } catch (Exception e) {
            System.err.println("error connecting to DB " + e.toString());
        }
    }

    /**
     * save an object
     * @param item
     */
    public void saveNew(Object item) {
        // Make persistent
        try {
            // transaction:
            pm.currentTransaction().begin();
            pm.makePersistent(item);
            pm.currentTransaction().commit();
        } finally {
            // Close the active transaction:
            if (pm.currentTransaction().isActive()) {
                pm.currentTransaction().rollback();
            }
        }
    }

    /**
     * system print list of objects
     * @param aClass
     */
    public void dumpObjects(Class aClass) {

        // Get records
        System.out.println("...");
        System.out.println("Dumping data ..." + aClass.getName());

        List<Object> results = this.getObjects(aClass);

        System.out.println("Dump all Records of class => " + aClass.getName());
        for (Object p : results) {
            System.out.println(aClass.getName() + "=> " + p.toString());
        }
    }

    /**
     * get objects on class
     * @param aClass
     * @return  list of objects
     */
    public List<Object> getObjects(Class aClass) {

        List<Object> results;
        results = new ArrayList<>();

        // include instances of subclasses ... works using either true OR false?
//        Extent extent = pm.getExtent(tempClass, false);
        Extent extent = pm.getExtent(aClass, true);

        // Iterate, picking up all records and adding to list
        Iterator itr = extent.iterator();
        while (itr.hasNext()) {
            Object p = (Object) itr.next();
            results.add(p);
            //System.out.println(p);  //too many prints with thisa one
        }
        extent.closeAll();

        return results;
    }


    public List<Vehicle02> getVehicleObjects(Class aClass) {

        List<Vehicle02> results;
        results = new ArrayList<>();

        // include instances of subclasses ... works using either true OR false?
//        Extent extent = pm.getExtent(tempClass, false);
        Extent extent = pm.getExtent(aClass, true);

        // Iterate, picking up all records and adding to list
        Iterator itr = extent.iterator();
        while (itr.hasNext()) {
            Object p = (Object) itr.next();
            results.add((Vehicle02) p);
            //System.out.println(p);  //too many prints with thisa one
        }
        extent.closeAll();

        return results;
    }

    /**
     * remove items from database
     * @param aClass
     */
    public void emptyDB(Class aClass) {

        List<Object> vehicles = getObjects(aClass);

        try {
            pm.currentTransaction().begin();
            pm.deletePersistentAll(vehicles);
            pm.currentTransaction().commit();
        }finally {
            // Close the active transaction:
            if (pm.currentTransaction().isActive()) {
                pm.currentTransaction().rollback();
            }
        }
    }

    public void addObjDbObjects(){

    }


    /**
     * close connection
     */
    void close() {
        if (pm.currentTransaction().isActive()) {
            pm.currentTransaction().rollback();
        }
        if (!pm.isClosed()) {
            pm.close();
        }
    }




}
