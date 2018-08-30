/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.service.custom.impl;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.exam.business.BOFactory;
import lk.ijse.exam.business.custom.TeacherBO;
import lk.ijse.exam.dto.PaperDTO;
import lk.ijse.exam.dto.SemesterSubjectDTO;
import lk.ijse.exam.dto.TeacherDTO;
import lk.ijse.exam.observer.Observer;
import lk.ijse.exam.observer.Subject;
import lk.ijse.exam.service.custom.TeacherService;

/**
 *
 * @author Imalka Gunawardana
 */
public class TeacherServiceImpl extends UnicastRemoteObject implements TeacherService, Subject {

    private static ArrayList<Observer> observers;
    private TeacherBO teacherBO;

    static {
        observers = new ArrayList<>();
    }

    public TeacherServiceImpl() throws RemoteException {
        teacherBO = (TeacherBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.TEACHER);
    }

    @Override
    public TeacherDTO chkLogin(TeacherDTO teacherDTO) throws Exception {
        return teacherBO.chkLogin(teacherDTO);
    }

    @Override
    public void registerObserver(Observer observer) throws Exception {
        observers.add(observer);
        notifyObservers(0);
    }

    @Override
    public void unregisterObserver(Observer observer) throws Exception {
        observers.remove(observer);
        //notifyObservers(0);
    }

    @Override
    public void notifyObservers(int value) throws Exception {
        for (Observer observer : observers) {
            try {
                observer.updateObservers(value);
            } catch (Exception ex) {
                Logger.getLogger(TeacherServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public ArrayList<Observer> getObservers() throws Exception {
        return observers;
    }

    @Override
    public TeacherDTO getTeacherDetails(int id) throws Exception {
        return teacherBO.getTeacherDetails(id);
    }

    @Override
    public List<SemesterSubjectDTO> loadSubjects(int id) throws Exception {
        return teacherBO.loadSubjects(id);
    }

    @Override
    public String getTeacherName(int id) throws Exception {
        return teacherBO.getTeacherName(id);
    }

    @Override
    public List<String> getQuestionPapers(PaperDTO paperDTO) throws Exception {
        return teacherBO.getQuestionPapers(paperDTO);
    }

    @Override
    public int getQuestionsCount(PaperDTO paperDTO) throws Exception {
        return teacherBO.getQuestionsCount(paperDTO);
    }

    @Override
    public File getFilePath(String name) throws Exception {
        return teacherBO.getFilePath(name);
    }
}
