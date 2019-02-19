/**
 * @author Bikas Anand
 */
package com.anand.springproject.service.controller;

import com.anand.springproject.core.domain.State;
import com.anand.springproject.service.utils.ServiceState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
@RequestMapping(value = StatusController.STATUS_URL)
public class StatusController {
    public static final String STATUS_URL = "/springproject/status";
    public static final String STATUS_URL_REFRESH = "/springproject/status/refresh";


    @Autowired
    private ServiceState serviceState;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<State> getState(){

        final State state = new State();
        state.setState(serviceState.getCurrentState());
        return new ResponseEntity<>(state, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setState(@RequestBody final State state){
        serviceState.setState(state.getState());
        return new ResponseEntity<>("State set to " + state.getState() , HttpStatus.OK);
    }

    @RequestMapping(
            value = STATUS_URL_REFRESH,
            method = RequestMethod.PUT)
    public ResponseEntity<String> refreshState(){

        serviceState.refreshState();
        return new ResponseEntity<>("State refreshed", HttpStatus.OK);
    }

}
