/**
 * @author Bikas Anand
 */
package com.anand.springproject.service.controller;

import com.anand.springproject.core.domain.User;
import com.anand.springproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
@RequestMapping(value = UserController.DOC_URL)
public class UserController {
    public static final String DOC_URL = "/springproject/v1/users";

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "{id:.+}",
            method = RequestMethod.GET)
    public ResponseEntity<User> getUser(
            @PathVariable final Integer id) throws Exception{

        User user = userService.getUser(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public ResponseEntity<Iterable<User>> getUsers()throws Exception{

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<Integer> createUser(@RequestBody User user) throws Exception{

        int id = userService.createUser(user);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
