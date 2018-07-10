/**
 * @author Bikas Anand
 */
package com.anand.springproject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
@RequestMapping(value = VersionController.VERSION_URL)
public class VersionController
{
    public static final String VERSION_URL = "/springproject/version";

    //TODO: Access logger

    @Value("${version:0.0.0}")
    private String serviceVersion;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getState(){

        return new ResponseEntity<>(serviceVersion, HttpStatus.OK);
    }
}
