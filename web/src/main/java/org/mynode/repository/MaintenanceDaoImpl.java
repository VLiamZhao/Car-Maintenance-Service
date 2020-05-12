package org.mynode.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mynode.model.Maintenance;
import org.mynode.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaintenanceDaoImpl implements MaintenanceDao {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Maintenance save(Maintenance maintenance) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(maintenance);
            transaction.commit();
            return maintenance;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteMaintenanceById(long id) {
        String hql = "DELETE Maintenance where id = :targetId";
        int deletedCount = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("targetId", id);
            deletedCount = query.executeUpdate();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The maintenance which id is %s was deleted",id));
        return deletedCount == 1;
    }

    @Override
    public List<Maintenance> getMaintenanceOrders() {
        String hql = "From Maintenance";
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(hql);
            return (List<Maintenance>)query.list();
        }
        catch (Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public Maintenance getMaintenanceById(long id) {
        String hql = "FROM Maintenance as ma where ma.id = :targetId";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Maintenance> query = session.createQuery(hql);
            query.setParameter("targetId", id);
            return query.uniqueResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

//    public static void main(String[] args) {
//        MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
//        System.out.println(maintenanceDao.getMaintenanceOrders().size());
//
//    }
}
