/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.controller;

import java.util.List;
import java.util.Map;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.observer.Subject;
import lk.ijse.exam.proxy.ProxyHandler;
import lk.ijse.exam.reservation.Reservation;
import lk.ijse.exam.service.ServiceFactory;
import lk.ijse.exam.service.custom.AdminService;

/**
 *
 * @author Imalka Gunawardana
 */
public class StudentPanelController {

    /*For Network*/
    public static Subject getAdminSubject() throws Exception {
        return (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
    }

    public static Reservation getAdminReservation() throws Exception {
        return (Reservation) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
    }

    public static boolean reserve(String key, String value) throws Exception {
        AdminService adminService = (AdminService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
        return adminService.reserve(key, value);
    }

    public static boolean release(String key, String value) throws Exception {
        AdminService adminService = (AdminService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
        return adminService.release(key, value);
    }

    public static Map<String, List<String>> getReservations() throws Exception {
        AdminService adminService = (AdminService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
        return adminService.getReservations();
    }

    
    /*Database Accessed Data*/
    public static List<String> getBatches() throws Exception {
        AdminService adminService = (AdminService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
        return adminService.getBatches();
    }

    public static List<String> getSemesters() throws Exception {
        AdminService adminService = (AdminService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
        return adminService.getSemesters();
    }

    public static List<String> getCategories() throws Exception {
        AdminService adminService = (AdminService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
        return adminService.getCategories();
    }
    
    public static List<StudentDTO> getAllStudents() throws Exception {
        AdminService adminService = (AdminService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
        return adminService.getAllStudents();
    }

    public static boolean saveStudent(StudentDTO studentDTO) throws Exception {
        AdminService adminService = (AdminService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
        return adminService.saveStudent(studentDTO);
    }

    public static boolean updateStudent(StudentDTO studentDTO) throws Exception {
        AdminService adminService = (AdminService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
        return adminService.updateStudent(studentDTO);
    }
}
