package com.anand.springproject.library.csv;

import com.anand.springproject.core.exception.UnexpectedException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class CSVFileWriter {

    private static final XLogger logger = XLoggerFactory.getXLogger(CSVFileWriter.class);

    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final Character DEFAULT_DELIMITER = ',';

    private static final CSVFormat DEFAULT_CSV_FORMAT
            = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

    private String csvFileName;
    private FileWriter fileWriter;
    private CSVPrinter csvFilePrinter;

    /**
     *
     * @param csvFileName
     * @param headerLine
     * @throws IOException
     */
    private CSVFileWriter(String csvFileName, String[] headerLine) throws IOException {

        this(csvFileName, headerLine, DEFAULT_DELIMITER);
    }

    /**
     *
     * @param csvFileName
     * @param headerLine
     * @param delimiter
     * @throws IOException
     */
    private CSVFileWriter(String csvFileName, String[] headerLine, Character delimiter) throws IOException {

        this.csvFileName = csvFileName;
        try {
            fileWriter = new FileWriter(csvFileName);
            csvFilePrinter = new CSVPrinter(fileWriter, DEFAULT_CSV_FORMAT.withDelimiter(delimiter).withHeader(headerLine));
        } catch (Exception e){
            throw logger.throwing(XLogger.Level.ERROR, new UnexpectedException(String.format("Error occurred while creating CSV file: %s.", csvFileName), e));
        }
    }

    /**
     *
     */
    public void close() {

        try {
            fileWriter.flush();
            fileWriter.close();
            csvFilePrinter.close();
            logger.info("CSV file created {}.", csvFileName);

        } catch (Exception e){
            logger.error("Error occurred while creating CSV file");
        }
    }

    /**
     *
     * @param line
     * @throws IOException
     */
    public void writeLine(List<String> line) throws IOException{

        try{
            csvFilePrinter.printRecord(line);
            fileWriter.flush();

        } catch (IOException ex){
            logger.error("Error printing csv record: {}", line.toString());
            throw ex;
        }

    }

    /**
     * Factory method
     *
     * @param csvFileName
     * @param headerLine
     * @return
     * @throws IOException
     */
    public static CSVFileWriter getWriterObject(String csvFileName, String[] headerLine) throws IOException{
        return new CSVFileWriter(csvFileName, headerLine);

    }

    /**
     * Factory method
     *
     * @param csvFileName
     * @param headerLine
     * @return
     * @throws IOException
     */
    public static CSVFileWriter getWriterObject(String csvFileName, String[] headerLine, Character delimiter) throws IOException{
        return new CSVFileWriter(csvFileName, headerLine, delimiter);

    }
}
