package org.srvm.dao;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.io.Serializable;

public interface GenericDAO<T, ID extends Serializable> {

    T findById(ID id);

    T findById(ID id, LockModeType lockModeType);

    T findReferenceById(ID id);

    T makePersistent(T entity);

    void setEntityManager(EntityManager em);

    EntityManager getEntityManager();

    void makeTransient(T entity);

    void checkVersion(T entity, boolean forceUpdate);

}
