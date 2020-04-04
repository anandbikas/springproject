package com.anand.springproject.service;

import com.anand.springproject.core.domain.orm.User;
import com.anand.springproject.core.exception.NotFoundException;
import com.anand.springproject.library.sql.UserRepository;
import com.anand.springproject.library.util.Util;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserService {

    private static final XLogger logger = XLoggerFactory.getXLogger(UserService.class);

    private UserRepository userRepo;

    @Autowired
    private Util util;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public User getUser(int userId) throws Exception{
        logger.info("Processing getUser request for userId: {}", userId);
        return userRepo.findById(userId).orElse(null);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public Iterable<User> getAllUsers(String firstName, String lastName) throws Exception{
        logger.info("Processing getAllUsers request");

        Iterable<User> users;

        if(lastName==null){
            if(firstName==null){
                users = userRepo.findAll();
            } else {
                users = userRepo.findByFirstName(firstName);
            }
        } else {
            if(firstName==null){
                users = userRepo.findByLastName(lastName);
            } else {
                users = userRepo.findByFirstNameAndLastName(firstName, lastName);
            }
        }

        return users;
    }


    /**
     *
     * @param id
     * @param email
     * @return
     * @throws Exception
     */
    public User updateEmailForId(int id, String email) throws Exception{
        logger.info("Processing updateEmailForId request");

        User user = userRepo.findById(id).orElse(null);

        if(user == null){
            throw logger.throwing(new NotFoundException(String.format("User with id: %d not found", id)));
        }

        int updatedCount = userRepo.updateEmailForId(id, email);
        logger.debug("Records updated: " + updatedCount);
        return userRepo.findById(id).orElse(null);
    }
    /**
     *
     * @param user
     * @return
     */
    public int createUser(User user){
        User userCreated = userRepo.save(user);
        logger.info("Created user: {}", util.prettyString(userCreated));

        return userCreated.getId();
    }
}
