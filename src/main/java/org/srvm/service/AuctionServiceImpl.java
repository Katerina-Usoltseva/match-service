package org.srvm.service;

import org.srvm.dao.UserDAO;
import org.srvm.dao.UserDAOImpl;
import org.srvm.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@javax.ejb.Stateless
@javax.ejb.Local(AuctionService.class)
public class AuctionServiceImpl implements AuctionService {
    @Inject
    private UserDAO userDAO = new UserDAOImpl();

    public User findUser(Long id) {
        return userDAO.findById(id, LockModeType.NONE);
    }

    @Override
    public User storeUser(User user) {
        return userDAO.makePersistent(user);
    }

    @Override
    public void setEntityManager(EntityManager em) {
        userDAO.setEntityManager(em);
    }

    @Override
    public List<?> getUserMatches(String interests) {
        return userDAO.getUserMatches(interests);
    }
}
