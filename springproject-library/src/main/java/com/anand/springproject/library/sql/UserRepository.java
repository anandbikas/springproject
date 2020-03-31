package com.anand.springproject.library.sql;

import com.anand.springproject.core.domain.orm.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
