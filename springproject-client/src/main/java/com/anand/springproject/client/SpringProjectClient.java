package com.anand.springproject.client;

import com.anand.springproject.core.domain.State;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SpringProjectClient {

    private static final XLogger logger = XLoggerFactory.getXLogger(SpringProjectClient.class);

    private static final String SPRING_PROJECT_STATUS_URI = "/springproject/status";

    @Value("${springproject.server.url:https://springproject.com:8443}")
    protected String springProjectServerBaseUrl;

    @Autowired
    @Qualifier("sslRestTemplate")
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    /**
     *
     * @return
     */
    public int getSpringProjectStatus(){

        final String resourceUrl = springProjectServerBaseUrl + SPRING_PROJECT_STATUS_URI;

        ResponseEntity<State> responseEntity
                = restTemplate.getForEntity(resourceUrl, State.class);
        logger.debug("getSpringProjectStatus response: " + prettyString(responseEntity.getBody()));

        return responseEntity.getBody().getState();
    }

    /**
     *
     * @return
     */
    public void setSpringProjectStatus(final int stateValue){

        final String resourceUrl = springProjectServerBaseUrl + SPRING_PROJECT_STATUS_URI;

        final State state = new State();
        state.setState(stateValue);
        ResponseEntity<String> responseEntity
                = restTemplate.postForEntity(resourceUrl, state, String.class);
        logger.debug("setSpringProjectStatus response: " + prettyString(responseEntity.getBody()));

    }

    /**
     *
     * @param object
     * @return
     */
    protected String prettyString(Object object) {

        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e){
            return "Exception in objectMapper";
        }
    }

}
