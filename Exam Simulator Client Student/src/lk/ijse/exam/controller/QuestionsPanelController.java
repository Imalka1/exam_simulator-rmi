/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.controller;

import lk.ijse.exam.dto.PaperDTO;
import lk.ijse.exam.proxy.ProxyHandler;
import lk.ijse.exam.service.ServiceFactory;
import lk.ijse.exam.service.custom.StudentService;

/**
 *
 * @author Imalka Gunawardana
 */
public class QuestionsPanelController {

    public static PaperDTO generateQuestionsByStudent(PaperDTO paperDTO) throws Exception {
        StudentService studentService = (StudentService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.STUDENT);
        return studentService.generateQuestionsByStudent(paperDTO);
    }
}
