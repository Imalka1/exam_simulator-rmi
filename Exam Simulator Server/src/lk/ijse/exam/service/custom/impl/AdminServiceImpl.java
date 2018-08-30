/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.exam.business.BOFactory;
import lk.ijse.exam.business.custom.AdminBO;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.observer.Observer;
import lk.ijse.exam.observer.Subject;
import lk.ijse.exam.reservation.Reservation;
import lk.ijse.exam.reservation.impl.ReservationImpl;
import lk.ijse.exam.service.custom.AdminService;

/**
 *
 * @author Imalka Gunawardana
 */
public class AdminServiceImpl extends UnicastRemoteObject implements AdminService, Subject {

    private AdminBO adminBO;
    private static ArrayList<Observer> observers = new ArrayList<>();
    private static Reservation studentsRes = new ReservationImpl();

    public AdminServiceImpl() throws RemoteException {
        adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);
    }

    @Override
    public void registerObserver(Observer observer) throws Exception {
        observers.add(observer);
        notifyObservers(0);
    }

    @Override
    public void unregisterObserver(Observer observer) throws Exception {
        observers.remove(observer);
        notifyObservers(0);
    }

    @Override
    public void notifyObservers(int value) throws Exception {
        for (Observer observer : observers) {
            try {
                observer.updateObservers(value);
            } catch (Exception ex) {
                Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Observer> getObservers() throws Exception {
        return observers;
    }

    @Override
    public boolean reserve(String key, String value) throws Exception {
        boolean reserve = studentsRes.reserve(key, value);
        notifyObservers(0);
        return reserve;
    }

    @Override
    public boolean release(String key, String value) throws Exception {
        boolean release = studentsRes.release(key, value);
        notifyObservers(0);
        return release;
    }

    @Override
    public Map<String, List<String>> getReservations() throws Exception {
        return studentsRes.getReservations();
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        return adminBO.getAllStudents();
    }

    @Override
    public List<String> getBatches() throws Exception {
        return adminBO.getBatches();
    }

    @Override
    public List<String> getSemesters() throws Exception {
        return adminBO.getSemesters();
    }

    @Override
    public List<String> getCategories() throws Exception {
        return adminBO.getCategories();
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws Exception {
        return adminBO.saveStudent(studentDTO);
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        return adminBO.updateStudent(studentDTO);
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) throws Exception {
        return adminBO.deleteStudent(studentDTO);
    }
}
