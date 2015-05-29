package com.damdinov.server;

import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ModelDaoImpl implements ModelDao{
    @Override
    public void addModel(ModelsEntity model) throws SQLException {
        Session session = null;

        //TODO: try with resources everywhere where try
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(model);
            session.getTransaction().commit();
        } catch ( Exception e){
            e.printStackTrace();
        } finally {
            if (null != session && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void deleteModel(ModelsEntity model) throws SQLException {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(model);
            session.getTransaction().commit();
        } catch ( Exception e){
            e.printStackTrace();
        } finally {
            if (null != session && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List getModel(double latitude, double longitude) throws SQLException {
        Session session = null;
        List list = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ModelsEntity where latitude = :lat and longitude = :lon");
            query.setParameter("lat", latitude);
            query.setParameter("lon", longitude);
            list = query.list();
        } catch ( Exception e){
            e.printStackTrace();
        } finally {
            if (null != session && session.isOpen()){
                session.close();
            }
        }
        return list;
    }

    @Override
    public List getModels() throws SQLException {
        List list = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createCriteria(ModelsEntity.class).list();
        } finally {
            if (session != null){
                session.close();
            }
        }
        return list;
    }

}
