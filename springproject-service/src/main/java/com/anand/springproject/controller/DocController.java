/**
 * @author Bikas Anand
 */
package com.anand.springproject.controller;

import com.anand.springproject.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
@RequestMapping(value = DocController.DOC_URL)
public class DocController {
    public static final String DOC_URL = "/docs";

    @Autowired
    DocService docService;

    @RequestMapping(
            value = "v1/{namespace}/{id:.+}",
            method = RequestMethod.GET)
    public ResponseEntity<String> getDocument(
            @PathVariable final String namespace,
            @PathVariable final Integer id) throws Exception{

        return new ResponseEntity(docService.getDocument(namespace, id), HttpStatus.OK);
    }

    @RequestMapping(
            value = "v1/{namespace}",
            method = RequestMethod.GET)
    public ResponseEntity<String> getDocuments(
            @PathVariable final String namespace) throws Exception{

        return new ResponseEntity(docService.getDocuments(namespace), HttpStatus.OK);
    }
}
