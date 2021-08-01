package org.srvm.dao;

import org.srvm.model.User;

import javax.persistence.LockModeType;
import java.util.List;

public interface UserDAO extends GenericDAO<User, Long> {
    User findById(Long id, LockModeType lockModeType);
    List<?> getUserMatches(String interests);
}
