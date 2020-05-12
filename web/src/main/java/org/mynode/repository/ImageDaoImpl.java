package org.mynode.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mynode.model.Customer;
import org.mynode.model.Image;
import org.mynode.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ImageDaoImpl implements ImageDao {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Image save(Image image) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(image);
            transaction.commit();
            return image;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Image getImageById(long id) {
        String hql = "FROM Image as im where im.id = :targetId";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Image> query = session.createQuery(hql);
            query.setParameter("targetId", id);
            return query.uniqueResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        String hql = "DELETE Image where id = :targetId";
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
        logger.debug(String.format("The image which id is %s was deleted",id));
        return deletedCount == 1;
    }

    @Override
    public List<Image> getAllImages() {
        String hql = "From Image";
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(hql);
            return (List<Image>) query.list();
        }
        catch (Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Image> getImagesByUserId(long id) {
        String hql = "FROM Customer as cu where cu.id = :targetId";
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("targetId", id);
            return query.uniqueResult().getImages();
        }
        catch (Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }
}
