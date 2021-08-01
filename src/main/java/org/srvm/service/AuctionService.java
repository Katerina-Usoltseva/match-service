package org.srvm.service;

import org.srvm.model.User;

import javax.persistence.EntityManager;
import java.util.List;

public interface  AuctionService {
    User findUser(Long id);
    User storeUser(User user);
    List<?> getUserMatches(String interests);

    void setEntityManager(EntityManager em);
}
