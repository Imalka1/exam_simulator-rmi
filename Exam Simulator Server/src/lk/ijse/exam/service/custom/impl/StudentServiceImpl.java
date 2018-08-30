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
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.exam.business.BOFactory;
import lk.ijse.exam.business.custom.StudentBO;
import lk.ijse.exam.dto.PaperDTO;
import lk.ijse.exam.dto.SemesterDTO;
import lk.ijse.exam.dto.SemesterSubjectDTO;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.dto.SubjectDTO;
import lk.ijse.exam.dto.TeacherDTO;
import lk.ijse.exam.observer.Observer;
import lk.ijse.exam.observer.Subject;
import lk.ijse.exam.service.custom.StudentService;

/**
 *
 * @author Imalka Gunawardana
 */
public class StudentServiceImpl extends UnicastRemoteObject implements StudentService, Subject {

    private static ArrayList<Observer> observers;
    private StudentBO studentBO;

    static {
        observers = new ArrayList<>();
    }

    public StudentServiceImpl() throws RemoteException {
        studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    }

    @Override
    public int chkLogin(StudentDTO studentDTO) throws Exception {
        return studentBO.chkLogin(studentDTO);
    }

    @Override
    public void registerObserver(Observer observer) throws Exception {
        observers.add(observer);
        //notifyObservers(0);
    }

    @Override
    public void unregisterObserver(Observer observer) throws Exception {
        observers.remove(observer);
        // notifyObservers(0);
    }

    @Override
    public void notifyObservers(int value) throws Exception {
        for (Observer observer : observers) {
            try {
                observer.updateObservers(0);
            } catch (Exception ex) {
                Logger.getLogger(StudentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public ArrayList<Observer> getObservers() throws Exception {
        return observers;
    }

    @Override
    public StudentDTO getStudentDetails(int id) throws Exception {
        return studentBO.getStudentDetails(id);
    }

    @Override
    public List<String> getSemesters() throws Exception {
        return studentBO.getSemesters();
    }

    @Override
    public List<TeacherDTO> loadTeachers(String id) throws Exception {
        return studentBO.loadTeachers(id);
    }

    @Override
    public List<SubjectDTO> loadSubjects(SemesterSubjectDTO semesterSubjectDTO) throws Exception {
        return studentBO.loadSubjects(semesterSubjectDTO);
    }

    @Override
    public List<SubjectDTO> loadCategories(String id) throws Exception {
        return studentBO.loadCategories(id);
    }

    @Override
    public PaperDTO generateQuestionsByStudent(PaperDTO paperDTO) throws Exception {
        return studentBO.generateModelQuestionPaper(paperDTO);
    }

    @Override
    public List<String> getModelQuestionPapers(PaperDTO paperDTO) throws Exception {
        return studentBO.getModelQuestionPapers(paperDTO);
    }
}
