/**
 * @author Bikas Anand
 */
package com.anand.springproject.service.controller;

import com.anand.springproject.core.domain.orm.User;
import com.anand.springproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Controller
@RequestMapping(value = UserController.DOC_URL)
public class UserController {
    public static final String DOC_URL = "/v1/users";

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
    public ResponseEntity<Iterable<User>> getUsers(
            @RequestParam(name=User.FIRST_NAME, required = false) String firstName,
            @RequestParam(name=User.LAST_NAME, required = false) String lastName)throws Exception{

        return new ResponseEntity<>(userService.getAllUsers(firstName, lastName), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/updateEmailForId",
            method = RequestMethod.GET)
    public ResponseEntity<User> updateEmailForId(
            @RequestParam(name=User.ID, required = true) int id,
            @RequestParam(name=User.EMAIL, required = true) String email)throws Exception{

        return new ResponseEntity<>(userService.updateEmailForId(id, email), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<Integer> createUser(@RequestBody User user) throws Exception{

        int id = userService.createUser(user);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
