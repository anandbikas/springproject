package com.anand.springproject.library.csv;

import com.anand.springproject.core.exception.UnexpectedException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class CSVFileReader {

    private static final XLogger logger = XLoggerFactory.getXLogger(CSVFileReader.class);

    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final Character DEFAULT_DELIMITER = ',';

    private static final CSVFormat DEFAULT_CSV_FORMAT = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

    private String fileName;
    Iterable<CSVRecord> records;
    Iterator<CSVRecord> recordIterator;

    /**
     *
     * @param fileName
     * @throws IOException
     */
    private CSVFileReader(String fileName) throws IOException {

        this(fileName,DEFAULT_DELIMITER);
    }

    /**
     *
     * @param fileName
     * @throws IOException
     */
    private CSVFileReader(String fileName, Character delimiter) throws IOException {

        this.fileName = fileName;
        try {
            Reader in = new FileReader(fileName);
            records = CSVFormat.DEFAULT.withDelimiter(delimiter).withFirstRecordAsHeader().parse(in);
            recordIterator = records.iterator();

        } catch (Exception e){
            throw logger.throwing(XLogger.Level.ERROR, new UnexpectedException(String.format("Error occurred while opening CSV file: %s.", fileName), e));
        }
    }

    /**
     *
     * @return
     */
    public boolean hasNextRecord(){
        return recordIterator.hasNext();
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public CSVRecord getNextRecord(){

        records.iterator().hasNext();
        return recordIterator.next();
    }

    /**
     * Factory method
     *
     * @param csvFileName
     * @return
     * @throws IOException
     */
    public static CSVFileReader getReaderObject(String csvFileName) throws IOException{
        return new CSVFileReader(csvFileName);

    }

    /**
     * Factory method
     *
     * @param csvFileName
     * @return
     * @throws IOException
     */
    public static CSVFileReader getReaderObject(String csvFileName, Character delimiter) throws IOException{
        return new CSVFileReader(csvFileName, delimiter);

    }
}
