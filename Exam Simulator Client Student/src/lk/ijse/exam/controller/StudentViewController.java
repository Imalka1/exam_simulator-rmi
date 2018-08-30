/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.controller;

import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.observer.Subject;
import lk.ijse.exam.proxy.ProxyHandler;
import lk.ijse.exam.service.ServiceFactory;
import lk.ijse.exam.service.custom.StudentService;

/**
 *
 * @author Imalka Gunawardana
 */
public class StudentViewController {

    public static Subject getTeacherSubject() throws Exception {
        return (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TEACHER);
    }
    
    public static Subject getStudentSubject() throws Exception {
        return (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
    }

    public static StudentDTO getStudentDetails(int id) throws Exception {
        StudentService studentService = (StudentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.getStudentDetails(id);
    }
}
