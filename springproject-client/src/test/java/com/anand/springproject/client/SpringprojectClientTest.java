/**
 * @author Bikas Anand
 */
package com.anand.springproject.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = "classpath:spring/springproject-client-context.xml")
@SpringBootTest
public class SpringprojectClientTest {

    @Autowired
    SpringProjectClient springProjectClient;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetSpringProjectVersion(){
        int springProjectStatus = springProjectClient.getSpringProjectStatus();

    }

    @Test
    public void testSetSpringProjectVersion(){
        springProjectClient.setSpringProjectStatus(1);

    }

}
