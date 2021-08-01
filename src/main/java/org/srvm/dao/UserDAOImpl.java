package org.srvm.dao;

import org.srvm.model.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDAOImpl extends GenericDAOImpl<User, Long>
    implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User findById(Long id, LockModeType lockModeType) {

        return super.findById(id, lockModeType);
    }

    @Override
    public List<?> getUserMatches(String interests) {
        String hql = "select a from CommonAccount a where a.interests = :int";

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(hql).setParameter("int", "dogs");
        List<?> resultList = query.getResultList();
        em.getTransaction().commit();
        em.close();

        return resultList;
    }
}
