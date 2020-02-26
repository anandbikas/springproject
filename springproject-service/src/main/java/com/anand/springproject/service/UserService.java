package com.anand.springproject.service;

import com.anand.springproject.core.domain.User;
import com.anand.springproject.library.sql.UserRepository;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserService {

    private UserRepository userRepo;

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
        return userRepo.findById(userId).orElse(null);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public Iterable<User> getAllUsers() throws Exception{
        return userRepo.findAll();
    }

    /**
     *
     * @param user
     * @return
     */
    public int createUser(User user){
        User userCreated = userRepo.save(user);
        return userCreated.getId();
    }
}
