package com.anand.springproject.utils.code;

import com.anand.springproject.library.csv.CSVFileReader;
import com.anand.springproject.library.csv.CSVFileWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class Util {

    private static final XLogger logger = XLoggerFactory.getXLogger(Util.class);

    @Autowired
    protected ObjectMapper objectMapper;

    private final String NAME= "Name", PRICE="Price";

    @PostConstruct
    public void init() throws  Exception{

        // CSVFileWriter Demo
        CSVFileWriter.getWriterObject("abc.csv", new String[]{NAME,PRICE}, '|').writeLine(Arrays.asList("HP, monitor", "300"));

        //CSVFileReader Demo
        CSVFileReader csvFileReader =  CSVFileReader.getReaderObject("abc.csv", '|');
        while(csvFileReader.hasNextRecord()){
            CSVRecord record = csvFileReader.getNextRecord();
            logger.info(String.format("%s,%s", record.get(NAME), record.get(PRICE)));
        }
        logger.error("Error occurred");

    }

    private String prettyString(Object object) {

        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e){
            return "Exception in objectMapper";
        }
    }
}
