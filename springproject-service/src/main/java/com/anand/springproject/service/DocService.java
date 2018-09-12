package com.anand.springproject.service;

import com.anand.springproject.core.domain.Student;
import com.anand.springproject.utils.excel.ExcelDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class DocService {

    final static String STUDENTS = "students";

    @Autowired
    ExcelDocument excelDocument;

    /**
     *
     * @param rollNumber
     * @return
     */
    public Student getDocument(String namespace, int rollNumber) throws Exception{

        switch (namespace.toLowerCase()){
            case STUDENTS:
                Student student = new Student();

                student.setRollNumber(Integer.valueOf(excelDocument.getFieldValue(Student.ROLL_NUMBER, rollNumber)));
                student.setFirstName(excelDocument.getFieldValue(Student.FIRST_NAME, rollNumber));
                student.setLastName(excelDocument.getFieldValue(Student.LAST_NAME, rollNumber));
                student.setContact(excelDocument.getFieldValue(Student.CONTACT, rollNumber));
                student.setAddress(excelDocument.getFieldValue(Student.ADDRESS, rollNumber));
                student.setCity(excelDocument.getFieldValue(Student.CITY, rollNumber));

                return student;
            default:
                throw new Exception("Doc " + namespace + " not supported");
        }

    }

}
