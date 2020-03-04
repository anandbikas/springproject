package com.anand.springproject.service;

import com.anand.springproject.core.domain.User;
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
    public Iterable<User> getAllUsers() throws Exception{
        logger.info("Processing getAllUsers request");
        return userRepo.findAll();
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
