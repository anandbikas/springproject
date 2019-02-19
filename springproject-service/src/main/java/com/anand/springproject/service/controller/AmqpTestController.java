/**
 * @author Bikas Anand
 */
package com.anand.springproject.service.controller;

import com.anand.springproject.service.utils.AmqpDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
@RequestMapping(value = AmqpTestController.AMQP_TEST_URL)
public class AmqpTestController
{
    public static final String AMQP_TEST_URL = "/springproject/amqptest";

    @Autowired
    AmqpDemo amqpDemo;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> amqpTest(){

        amqpDemo.test();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
