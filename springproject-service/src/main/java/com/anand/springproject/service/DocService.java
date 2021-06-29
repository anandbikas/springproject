package com.anand.springproject.service;

import com.anand.springproject.core.domain.Student;
import com.anand.springproject.core.exception.NotFoundException;
import com.anand.springproject.library.excel.ExcelDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class DocService {

    final static String STUDENTS = "students";

    @Value("${excel.file.name.student}")
    private String excelFileNameStudent;

    private ExcelDocument excelDocument;

    /**
     *
     */
    @PostConstruct
    public void init(){
        excelDocument = new ExcelDocument(excelFileNameStudent);
    }

    /**
     * @param namespace
     * @param rollNumber
     * @return
     */
    public Student getDocument(String namespace, int rollNumber) throws Exception{

        return switch (namespace.toLowerCase()){
            case STUDENTS -> getStudentObj(rollNumber);
            default -> throw new NotFoundException("Doc " + namespace + " not found");
        };
    }

    /**
     *
     * @param namespace
     * @return
     */
    public List<Student> getDocuments(String namespace) throws Exception{

        return switch (namespace.toLowerCase()){
            case STUDENTS -> new ArrayList<>(){{
                    for(int row=1; row<excelDocument.getRows(); row++) {
                        add(getStudentObj(row));
                    }
                }};
            default -> throw new NotFoundException("Doc " + namespace + " not found");
        };
    }

    /**
     *
     * @param row
     * @return
     */
    private Student getStudentObj(final int row){
        Student student = new Student();

        student.setRollNumber(Integer.valueOf(excelDocument.getFieldValue(Student.ROLL_NUMBER, row)));
        student.setFirstName(excelDocument.getFieldValue(Student.FIRST_NAME, row));
        student.setLastName(excelDocument.getFieldValue(Student.LAST_NAME, row));
        student.setContact(excelDocument.getFieldValue(Student.CONTACT, row));
        student.setAddress(excelDocument.getFieldValue(Student.ADDRESS, row));
        student.setCity(excelDocument.getFieldValue(Student.CITY, row));

        return student;
    }
}
