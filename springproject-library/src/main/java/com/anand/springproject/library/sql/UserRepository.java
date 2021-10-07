package com.anand.springproject.library.sql;

import com.anand.springproject.core.domain.orm.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    //Supported all default CRUD operations from CrudRepository

    //Add automatic custom queries
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    //List<User> findByFirstNameAndLastName(String firstName, String lastName);
    List<User> findByFirstNameOrLastName(String firstName, String lastName);

    //Add manual custom queries
    @Query("select u from #{#entityName} u where lower(u.firstName) = lower(:firstName) and lower(u.lastName) = lower(:lastName)")
    List<User> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update #{#entityName} u set u.email = ?2 where u.id = ?1")
    int updateEmailForId(int id, String email);

}
