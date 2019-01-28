package com.anand.springproject.library.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.HashMap;

/**
 *
 */
public class ExcelDocument {

    private static final XLogger logger = XLoggerFactory.getXLogger(ExcelDocument.class);

    private String excelFileName;

    private static final DataFormatter dataFormatter = new DataFormatter();

    private HSSFSheet sheet;
    private int rows = 0;

    final HashMap<String, Integer> fieldCellMap = new HashMap<>();

    /**
     *
     */
    @PostConstruct
    public void readFile() {
        try{
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelFileName));

            HSSFWorkbook wb = new HSSFWorkbook(fs);
            sheet = wb.getSheetAt(0);
            rows = sheet.getPhysicalNumberOfRows();
            
        } catch (Exception ex){
            logger.debug("Failed to initialize excel document");
            rows=0;
            return;
        }

        //Populate FieldCellMap
        HSSFRow headerRow = sheet.getRow(0);
        for(int cell = 0; cell < headerRow.getPhysicalNumberOfCells(); cell++){
            HSSFCell hssfCell = headerRow.getCell(cell);
            final String field = dataFormatter.formatCellValue(hssfCell);

            if(StringUtils.isEmpty(field)){
                continue;
            }
            fieldCellMap.put(field, cell);
        }
        logger.info("ExcelDocument initialized...");
    }

    /**
     *
     * @param field
     * @param row
     * @return
     */
    public String getFieldValue(final String field, final int row){

        if(row>rows-1){
            throw new IndexOutOfBoundsException("Sheet row not found: " + row);
        }

        if(!fieldCellMap.containsKey(field)){
            throw new IllegalArgumentException("Field not found: " + field);
        }

        HSSFCell hssfCell = sheet.getRow(row).getCell(fieldCellMap.get(field));
        String value = dataFormatter.formatCellValue(hssfCell);
        return value;
    }

    /**
     *
     * @return
     */
    public int getRows() {
        return rows;
    }

    /**
     *
     * @return
     */
    public String getExcelFileName() {
        return excelFileName;
    }

    /**
     *
     * @param excelFileName
     */
    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }
}
