/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.controller;

import java.util.List;
import lk.ijse.exam.dto.PaperDTO;
import lk.ijse.exam.dto.SemesterSubjectDTO;
import lk.ijse.exam.dto.SubjectDTO;
import lk.ijse.exam.dto.TeacherDTO;
import lk.ijse.exam.observer.Subject;
import lk.ijse.exam.proxy.ProxyHandler;
import lk.ijse.exam.service.ServiceFactory;
import lk.ijse.exam.service.custom.StudentService;

/**
 *
 * @author Imalka Gunawardana
 */
public class TestExamPanelController {

    public static Subject getTeacherSubject() throws Exception {
        return (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TEACHER);
    }

    public static List<String> getSemesters() throws Exception {
        StudentService studentService = (StudentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.getSemesters();
    }

    public static List<TeacherDTO> loadTeachers(String id) throws Exception {
        StudentService studentService = (StudentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.loadTeachers(id);
    }

    public static List<SubjectDTO> loadSubjects(SemesterSubjectDTO semesterSubjectDTO) throws Exception {
        StudentService studentService = (StudentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.loadSubjects(semesterSubjectDTO);
    }

    public static List<SubjectDTO> loadCategories(String id) throws Exception {
        StudentService studentService = (StudentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.loadCategories(id);
    }
    
    public static List<String> getModelQuestionPapers(PaperDTO paperDTO) throws Exception {
        StudentService studentService = (StudentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.getModelQuestionPapers(paperDTO);
    }
}
