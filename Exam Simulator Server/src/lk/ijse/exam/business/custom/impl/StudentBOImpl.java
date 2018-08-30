/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.business.custom.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lk.ijse.exam.business.custom.StudentBO;
import lk.ijse.exam.dto.PaperDTO;
import lk.ijse.exam.dto.SemesterDTO;
import lk.ijse.exam.dto.SemesterSubjectDTO;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.dto.SubjectDTO;
import lk.ijse.exam.dto.TeacherDTO;
import lk.ijse.exam.entity.Semester;
import lk.ijse.exam.entity.SemesterSubject;
import lk.ijse.exam.entity.Student;
import lk.ijse.exam.entity.Teacher;
import lk.ijse.exam.repository.RepositoryFactory;
import lk.ijse.exam.repository.custom.SemesterRepository;
import lk.ijse.exam.repository.custom.StudentRepository;
import lk.ijse.exam.repository.custom.SubjectCategoryRepository;
import lk.ijse.exam.repository.custom.SubjectRepository;
import lk.ijse.exam.repository.custom.TeacherRepository;
import lk.ijse.exam.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Imalka Gunawardana
 */
public class StudentBOImpl implements StudentBO {

    private StudentRepository studentRepository;
    private SemesterRepository semesterRepository;
    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;
    private SubjectCategoryRepository subjectCategoryRepository;

    public StudentBOImpl() {
        studentRepository = (StudentRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.STUDENT);
        semesterRepository = (SemesterRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.SEMESTER);
        teacherRepository = (TeacherRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.TEACHER);
        subjectRepository = (SubjectRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.SUBJECT);
        subjectCategoryRepository = (SubjectCategoryRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.SUBJECTCATEGORY);
    }

    @Override
    public int chkLogin(StudentDTO studentDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            studentRepository.setSession(session);
            session.beginTransaction();
            Student student = new Student();
            student.setName(studentDTO.getName());
            student.setPassword(studentDTO.getPassword());
            int chkLogin = studentRepository.chkLogin(student);
            session.getTransaction().commit();
            return chkLogin;
        }
    }

    @Override
    public StudentDTO getStudentDetails(int id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            studentRepository.setSession(session);
            session.beginTransaction();
            StudentDTO studentDetails = studentRepository.getStudentDetails(id);
            session.getTransaction().commit();
            return studentDetails;
        }
    }

    @Override
    public List<String> getSemesters() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            semesterRepository.setSession(session);
            session.beginTransaction();
            List<String> loadSemesters = semesterRepository.getSemesters();
            session.getTransaction().commit();
            return loadSemesters;
        }
    }

    @Override
    public List<TeacherDTO> loadTeachers(String id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            teacherRepository.setSession(session);
            session.beginTransaction();
            List<TeacherDTO> loadTeachers = teacherRepository.loadTeachers(id);
            session.getTransaction().commit();
            return loadTeachers;
        }
    }

    @Override
    public List<SubjectDTO> loadSubjects(SemesterSubjectDTO semesterSubjectDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            subjectRepository.setSession(session);
            session.beginTransaction();
            SemesterSubject semesterSubject = new SemesterSubject();
            Teacher teacher = new Teacher();
            teacher.setName(semesterSubjectDTO.getTeacherName());
            Semester semester = new Semester();
            semester.setSemName(semesterSubjectDTO.getSemesterName());
            semesterSubject.setSemester(semester);
            semesterSubject.setTeacher(teacher);
            List<SubjectDTO> loadSubjects = subjectRepository.loadSubjects(semesterSubject);
            session.getTransaction().commit();
            return loadSubjects;
        }
    }

    @Override
    public List<SubjectDTO> loadCategories(String id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            subjectCategoryRepository.setSession(session);
            session.beginTransaction();
            List<SubjectDTO> loadCategories = subjectCategoryRepository.loadCategories(id);
            session.getTransaction().commit();
            return loadCategories;
        }
    }
    
    /*Question Paper Section*/

    /*Random Paper*/
    @Override
    public PaperDTO generateModelQuestionPaper(PaperDTO paperDTO) throws Exception {
        List<String> questionNo = new LinkedList();
        HashMap<String, String> questionWithNo = new HashMap();
        HashMap<String, Integer> answersCountWithNo = new HashMap();
        HashMap<String, List<String>> answersWithNo = new HashMap();
        int teacherId = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            teacherRepository.setSession(session);
            session.beginTransaction();
            SemesterSubject semesterSubject = new SemesterSubject();
            teacherId = teacherRepository.getTeacherId(paperDTO.getTeacherName());
            session.getTransaction().commit();
        }
        String[] split = paperDTO.getUrl().split("/");
        String firstQUrl = "questions/T" + teacherId + "/" + split[0].trim() + "/" + split[1].trim() + "/" + split[2].trim() + "/Questions (Model)/";
        String firstAUrl = "questions/T" + teacherId + "/" + split[0].trim() + "/" + split[1].trim() + "/" + split[2].trim() + "/Answers (Model)/";
        int countQ = (int) Files.list(Paths.get(firstQUrl)).count();
        int countA = (int) Files.list(Paths.get(firstAUrl)).count();
        String question = null;
        Set set = new HashSet();
        if (countQ >= paperDTO.getQuestionsCount() && teacherId != 0 && countQ == countA) {
            while (set.size() != paperDTO.getQuestionsCount()) {
                Random random = new Random();
                int no = random.nextInt(countQ) + 1;
                set.add(no);
            }
            Object[] toArray = set.toArray();
            for (int i = 0; i < paperDTO.getQuestionsCount(); i++) {
                questionNo.add("Question-" + (i + 1));
                /*Generate Questions*/
                String secondQUrl = firstQUrl.concat("Question-" + toArray[i] + ".txt");
                File file = new File(secondQUrl);
                if (file.exists()) {
                    BufferedReader readerQ = new BufferedReader(new FileReader(file));
                    String quQ = null;
                    while ((quQ = readerQ.readLine()) != null) {
                        if (question == null) {
                            question = quQ + "\n";
                        } else {
                            question = question + quQ + "\n";
                        }
                    }
                    questionWithNo.put("Question-" + (i + 1), question);
                    question = null;
                    /*Generate Answers*/
                    String secondAUrl = firstAUrl.concat("Question-" + toArray[i] + ".txt");
                    BufferedReader readerA = new BufferedReader(new FileReader(new File(secondAUrl)));
                    String[] answerSplit = readerA.readLine().split(",");
                    answersCountWithNo.put("Question-" + (i + 1), Integer.parseInt(answerSplit[0]));
                    List<String> answers = new LinkedList<>();
                    for (int j = 0; j < answerSplit.length - 1; j++) {
                        answers.add(answerSplit[j + 1]);
                    }
                    answersWithNo.put("Question-" + (i + 1), answers);
                } else {
                    continue;
                }
            }
        }
        PaperDTO answersDTO = new PaperDTO();
        answersDTO.setQuestionNo(questionNo);
        answersDTO.setQuestionWithNo(questionWithNo);
        answersDTO.setAnswersWithNo(answersWithNo);
        answersDTO.setAnswersCountWithNo(answersCountWithNo);
        return answersDTO;
    }

    /*Existing Paper*/
    @Override
    public PaperDTO getModelQuestionPaper(PaperDTO paperDTO) throws Exception {
        List<String> questionNo = new LinkedList();
        HashMap<String, String> questionWithNo = new HashMap();
        HashMap<String, Integer> answersCountWithNo = new HashMap();
        HashMap<String, List<String>> answersWithNo = new HashMap();
        int teacherId = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            teacherRepository.setSession(session);
            session.beginTransaction();
            SemesterSubject semesterSubject = new SemesterSubject();
            teacherId = teacherRepository.getTeacherId(paperDTO.getTeacherName());
            session.getTransaction().commit();
        }
        String[] split = paperDTO.getUrl().split("/");
        String firstQUrl = "questions/T" + teacherId + "/" + split[0].trim() + "/" + split[1].trim() + "/" + split[2].trim() + "/Questions (Model)/";
        String firstAUrl = "questions/T" + teacherId + "/" + split[0].trim() + "/" + split[1].trim() + "/" + split[2].trim() + "/Answers (Model)/";
        int countQ = (int) Files.list(Paths.get(firstQUrl)).count();
        int countA = (int) Files.list(Paths.get(firstAUrl)).count();
        String question = null;
        Set set = new HashSet();
        if (countQ >= paperDTO.getQuestionsCount() && teacherId != 0 && countQ == countA) {
            String[] questionNumbers = paperDTO.getQuestionPaper().split(",");
            for (int i = 0; i < questionNumbers.length; i++) {
                questionNo.add("Question-" + (i + 1));
                /*Generate Questions*/
                String secondQUrl = firstQUrl.concat("Question-" + questionNumbers[i] + ".txt");
                File file = new File(secondQUrl);
                if (file.exists()) {
                    BufferedReader readerQ = new BufferedReader(new FileReader(file));
                    String quQ = null;
                    while ((quQ = readerQ.readLine()) != null) {
                        if (question == null) {
                            question = quQ + "\n";
                        } else {
                            question = question + quQ + "\n";
                        }
                    }
                    questionWithNo.put("Question-" + (i + 1), question);
                    question = null;
                    /*Generate Answers*/
                    String secondAUrl = firstAUrl.concat("Question-" + questionNumbers[i] + ".txt");
                    BufferedReader readerA = new BufferedReader(new FileReader(new File(secondAUrl)));
                    String[] answerSplit = readerA.readLine().split(",");
                    answersCountWithNo.put("Question-" + (i + 1), Integer.parseInt(answerSplit[0]));
                    List<String> answers = new LinkedList<>();
                    for (int j = 0; j < answerSplit.length - 1; j++) {
                        answers.add(answerSplit[j + 1]);
                    }
                    answersWithNo.put("Question-" + (i + 1), answers);
                } else {
                    continue;
                }
            }
        }
        PaperDTO answersDTO = new PaperDTO();
        answersDTO.setQuestionNo(questionNo);
        answersDTO.setQuestionWithNo(questionWithNo);
        answersDTO.setAnswersWithNo(answersWithNo);
        answersDTO.setAnswersCountWithNo(answersCountWithNo);
        return answersDTO;
    }

    /*Existing Paper Modules*/
    @Override
    public List<String> getModelQuestionPapers(PaperDTO paperDTO) throws Exception {
        int teacherId = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            teacherRepository.setSession(session);
            session.beginTransaction();
            SemesterSubject semesterSubject = new SemesterSubject();
            teacherId = teacherRepository.getTeacherId(paperDTO.getTeacherName());
            session.getTransaction().commit();
        }
        List<String> fileNames = new ArrayList<>();
        String[] split = paperDTO.getUrl().split("/");
        File folder = new File("questions/T" + teacherId + "/" + split[0].trim() + "/" + split[1].trim() + "/" + split[2].trim() + "/Question Papers (Model)/");
        if (folder.isDirectory() && folder.exists()) {
            File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile() && listOfFiles[i].exists()) {
                    fileNames.add(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length() - 4));
                }
            }
        }
        return fileNames;
    }
}
