package com.anand.springproject.library.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class Util {

    @Autowired
    ObjectMapper objectMapper;

    /**
     *
     * @param object
     * @return
     */
    public String prettyString(Object object) {

        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e){
            return "Exception in objectMapper";
        }
    }
}
