package com.rvfs.challenge.mcc.currency.repository;

import com.rvfs.challenge.mcc.currency.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    /**
     * Find user by email.
     * @param email Email for search.
     * @return User data.
     */
    User findByEmail(String email);
}
