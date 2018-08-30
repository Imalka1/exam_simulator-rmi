/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.controller;

import java.io.File;
import lk.ijse.exam.proxy.ProxyHandler;
import lk.ijse.exam.service.ServiceFactory;
import lk.ijse.exam.service.custom.TeacherService;

/**
 *
 * @author Imalka Gunawardana
 */
public class SubjectsPanelController {

    public static File getFilePath(String name) throws Exception {
        TeacherService teacherService = (TeacherService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.TEACHER);
        return teacherService.getFilePath(name);
    }
}
