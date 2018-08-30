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
import java.util.Set;
import lk.ijse.exam.business.custom.TeacherBO;
import lk.ijse.exam.dto.PaperDTO;
import lk.ijse.exam.dto.SemesterSubjectDTO;
import lk.ijse.exam.dto.TeacherDTO;
import lk.ijse.exam.entity.SemesterSubject;
import lk.ijse.exam.entity.Teacher;
import lk.ijse.exam.repository.RepositoryFactory;
import lk.ijse.exam.repository.custom.SemesterSubjectRepository;
import lk.ijse.exam.repository.custom.SubjectRepository;
import lk.ijse.exam.repository.custom.TeacherRepository;
import lk.ijse.exam.resource.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Imalka Gunawardana
 */
public class TeacherBOImpl implements TeacherBO {

    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;
    private SemesterSubjectRepository semesterSubjectRepository;

    public TeacherBOImpl() {
        teacherRepository = (TeacherRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.TEACHER);
        subjectRepository = (SubjectRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.SUBJECT);
        semesterSubjectRepository = (SemesterSubjectRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.SEMESTERSUBJECT);
    }

    @Override
    public TeacherDTO chkLogin(TeacherDTO teacherDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            teacherRepository.setSession(session);
            session.beginTransaction();
            Teacher teacher = new Teacher();
            teacher.setName(teacherDTO.getName());
            teacher.setPassword(teacherDTO.getPassword());
            TeacherDTO chkLogin = teacherRepository.chkLogin(teacher);
            session.getTransaction().commit();
            return chkLogin;
        }
    }

    @Override
    public TeacherDTO getTeacherDetails(int id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            teacherRepository.setSession(session);
            session.beginTransaction();
            TeacherDTO teacherDetails = teacherRepository.getTeacherDetails(id);
            session.getTransaction().commit();
            return teacherDetails;
        }
    }

    @Override
    public List<SemesterSubjectDTO> loadSubjects(int id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            subjectRepository.setSession(session);
            session.beginTransaction();
            List<SemesterSubjectDTO> loadSubjects = subjectRepository.loadSubjects(id);
            session.getTransaction().commit();
            return loadSubjects;
        }
    }

    @Override
    public String getTeacherName(int id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            teacherRepository.setSession(session);
            session.beginTransaction();
            String teacherName = teacherRepository.getTeacherName(id);
            session.getTransaction().commit();
            return teacherName;
        }
    }
    
    /*Question Paper Section*/

    /*Questions Count of each paper*/
    @Override
    public int getQuestionsCount(PaperDTO paperDTO) throws Exception {
        int count = 0;
        String[] split = paperDTO.getUrl().split("/");
        File file = new File("questions/T" + paperDTO.getTid() + "/" + split[0].trim() + "/" + split[1].trim() + "/" + split[2].trim() + "/Question Papers/" + split[3].trim() + ".txt");
        if (file.isFile() && file.exists()) {
            BufferedReader readerQ = new BufferedReader(new FileReader(file));
            String quQ = null;
            String question = null;
            while ((quQ = readerQ.readLine()) != null) {
                if (question == null) {
                    question = quQ + "\n";
                } else {
                    question = question + quQ + "\n";
                }
            }
            String[] numbersCount = question.split(",");
            count = numbersCount.length;
        }
        return count;
    }

    /*Random Single Paper at once*/
    @Override
    public PaperDTO generateOneQuestionPaper(PaperDTO paperDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*Random Multiple Papers at once*/
    @Override
    public PaperDTO generateMultipleQuestionPapers(PaperDTO paperDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*Existing Paper*/
    @Override
    public PaperDTO getQuestionPaper(PaperDTO paperDTO) throws Exception {
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
        String firstQUrl = "questions/T" + paperDTO.getTid() + "/" + split[0].trim() + "/" + split[1].trim() + "/" + split[2].trim() + "/Questions/";
        String firstAUrl = "questions/T" + paperDTO.getTid() + "/" + split[0].trim() + "/" + split[1].trim() + "/" + split[2].trim() + "/Answers/";
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
    public List<String> getQuestionPapers(PaperDTO paperDTO) throws Exception {
        List<String> fileNames = new ArrayList<>();
        String[] split = paperDTO.getUrl().split("/");
        File folder = new File("questions/T" + paperDTO.getTid() + "/" + split[0].trim() + "/" + split[1].trim() + "/" + split[2].trim() + "/Question Papers/");
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

    /*Not used*/
    @Override
    public File getFilePath(String name) throws Exception {
        return new File("//localhost/questions/Semester-1/T1/Test");
    }
}
