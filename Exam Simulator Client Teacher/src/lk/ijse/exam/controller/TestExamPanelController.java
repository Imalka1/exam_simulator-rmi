/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.controller;

import java.util.List;
import lk.ijse.exam.dto.PaperDTO;
import lk.ijse.exam.dto.SemesterSubjectDTO;
import lk.ijse.exam.observer.Subject;
import lk.ijse.exam.proxy.ProxyHandler;
import lk.ijse.exam.service.ServiceFactory;
import lk.ijse.exam.service.custom.TeacherService;

/**
 *
 * @author Imalka Gunawardana
 */
public class TestExamPanelController {

    public static Subject getStudentSubject() throws Exception {
        return (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
    }

    public static List<SemesterSubjectDTO> loadSubjects(int id) throws Exception {
        TeacherService teacherService = (TeacherService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TEACHER);
        return teacherService.loadSubjects(id);
    }

    public static String getTeacherName(int id) throws Exception {
        TeacherService teacherService = (TeacherService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TEACHER);
        return teacherService.getTeacherName(id);
    }

    public static List<String> getQuestionPapers(PaperDTO paperDTO) throws Exception {
        TeacherService teacherService = (TeacherService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TEACHER);
        return teacherService.getQuestionPapers(paperDTO);
    }

    public static int getQuestionsCount(PaperDTO paperDTO) throws Exception {
        TeacherService teacherService = (TeacherService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TEACHER);
        return teacherService.getQuestionsCount(paperDTO);
    }
}
