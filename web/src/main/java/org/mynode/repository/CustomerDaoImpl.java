package org.mynode.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mynode.model.Customer;
import org.mynode.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CustomerDaoImpl implements CustomerDao {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Customer getCustomerEagerBy(long id) {
//        String hql = "From Customer as cu left join fetch cu.cars cas LEFT JOIN FETCH cu.images img WHERE cu.id = :cuId";
        String hql = "From Customer as cu left join fetch cu.cars as cas left join fetch cas.maintenances WHERE cu.id = :cuId";
//        String hql = "From Customer as cu left join fetch cu.images as img WHERE cu.id = :cuId";
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("cuId", id);
            return query.uniqueResult();
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }

        return null;
    }

    @Override
    public Customer getCustomerByName(String name) {
        String hql = "From Customer as cu WHERE lower(cu.name)= :cuName";
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("cuName", name.toLowerCase());
            return query.uniqueResult();
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> getCustomers() {
        String hql = "From Customer";
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            return query.list();
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Customer getCustomerById(long id) {
        String hql = "FROM Customer as cu where cu.id = :targetId";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("targetId", id);
            return query.uniqueResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            return customer;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
        return null;
    }


    @Override
    public boolean deleteById(long id) {
        String hql = "DELETE Customer where id = :targetId";
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
        logger.debug(String.format("The customer which id is %s was deleted", String.valueOf(id)));
        return deletedCount == 1;
    }

//    @Override
//    public boolean updateCustomer(Customer customer) {
//        String msg = String.format("The customer %s was updated.", customer.toString());
//        Transaction transaction = null;
//        boolean isSuccess = true;
//
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.saveOrUpdate(customer);
//            transaction.commit();
//        }
//        catch (Exception e) {
//            isSuccess = false;
//            msg = e.getMessage();
//            if (transaction != null) transaction.rollback();
//        }
//
//        logger.debug(msg);
//        return isSuccess;
//    }
//    public static void main(String[] args) {
//        CustomerDao customerDao = new CustomerDaoImpl();
//        System.out.println(customerDao.getCustomers().size());
//
//    }

    @Override
    public Customer getCustomerByCredentials(String emailOrUsername, String password) throws Exception {
        String hql = "FROM Customer as u left join fetch u.roleList where (lower(u.email) = :email or lower(u.name) =:email) and u.password = :password";
        logger.debug(String.format("User email: %s, password: %s", emailOrUsername, password));
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("email", emailOrUsername.toLowerCase().trim());
            query.setParameter("password", password);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new Exception("can't find user record or session");
        }
    }
}
