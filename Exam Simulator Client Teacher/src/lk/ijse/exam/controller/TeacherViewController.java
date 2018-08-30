/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.controller;

import lk.ijse.exam.dto.TeacherDTO;
import lk.ijse.exam.observer.Subject;
import lk.ijse.exam.proxy.ProxyHandler;
import lk.ijse.exam.service.ServiceFactory;
import lk.ijse.exam.service.custom.TeacherService;

/**
 *
 * @author Imalka Gunawardana
 */
public class TeacherViewController {

    public static Subject getTeacherSubject() throws Exception {
        return (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TEACHER);
    }

    public static TeacherDTO getTeacherDetails(int id) throws Exception {
        TeacherService teacherService = (TeacherService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TEACHER);
        return teacherService.getTeacherDetails(id);
    }
}
