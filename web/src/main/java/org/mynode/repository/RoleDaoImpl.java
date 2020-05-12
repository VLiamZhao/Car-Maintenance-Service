package org.mynode.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mynode.model.Role;
import org.mynode.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public Role getRoleByName(String name) {
    String hql = "FROM Role as r where lower(r.name) = :name";

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Query<Role> query = session.createQuery(hql);
      query.setParameter("name", name.toLowerCase());
      return query.uniqueResult();
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("Fail to get role.");
    }
    return null;
  }

    @Override
    public Role getRoleById(long id) {
        String hql = "FROM Role as r where r.id = :roId";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Role> query = session.createQuery(hql);
            query.setParameter("roId", id);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Fail to get role.");
        }
        return null;
    }
}