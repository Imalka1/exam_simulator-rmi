/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.business.custom.impl;

import java.util.List;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;
import lk.ijse.exam.business.custom.AdminBO;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.entity.Batch;
import lk.ijse.exam.entity.Registration;
import lk.ijse.exam.entity.RegistrationCategory;
import lk.ijse.exam.entity.RegistrationCategory_PK;
import lk.ijse.exam.entity.RegistrationSemester;
import lk.ijse.exam.entity.Semester;
import lk.ijse.exam.entity.Student;
import lk.ijse.exam.entity.SubjectCategory;
import lk.ijse.exam.repository.RepositoryFactory;
import lk.ijse.exam.repository.custom.BatchRepository;
import lk.ijse.exam.repository.custom.RegistrationCategoryRepository;
import lk.ijse.exam.repository.custom.RegistrationRepository;
import lk.ijse.exam.repository.custom.RegistrationSemesterRepository;
import lk.ijse.exam.repository.custom.SemesterRepository;
import lk.ijse.exam.repository.custom.StudentRepository;
import lk.ijse.exam.repository.custom.SubjectCategoryRepository;
import lk.ijse.exam.resource.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Imalka Gunawardana
 */

public class AdminBOImpl implements AdminBO {

    private StudentRepository studentRepository;
    private BatchRepository batchRepository;
    private SemesterRepository semesterRepository;
    private SubjectCategoryRepository subjectCategoryRepository;
    private RegistrationRepository registrationRepository;
    private RegistrationSemesterRepository registrationSemesterRepository;
    private RegistrationCategoryRepository registrationCategoryRepository;

    public AdminBOImpl() {
        studentRepository = (StudentRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.STUDENT);
        batchRepository = (BatchRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.BATCH);
        semesterRepository = (SemesterRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.SEMESTER);
        subjectCategoryRepository = (SubjectCategoryRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.SUBJECTCATEGORY);
        registrationRepository = (RegistrationRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.REGISTRATION);
        registrationSemesterRepository = (RegistrationSemesterRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.REGISTRATIONSEMESTER);
        registrationCategoryRepository = (RegistrationCategoryRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.REGISTRATIONCATEGORY);
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            studentRepository.setSession(session);
            session.beginTransaction();
            List<StudentDTO> allStudents = studentRepository.getAllStudents();
            session.getTransaction().commit();
            return allStudents;
        }
    }

    @Override
    public List<String> getBatches() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            batchRepository.setSession(session);
            session.beginTransaction();
            List<String> batches = batchRepository.getBatches();
            session.getTransaction().commit();
            return batches;
        }
    }

    @Override
    public List<String> getSemesters() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            semesterRepository.setSession(session);
            session.beginTransaction();
            List<String> semesters = semesterRepository.getSemesters();
            session.getTransaction().commit();
            return semesters;
        }
    }

    @Override
    public List<String> getCategories() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            subjectCategoryRepository.setSession(session);
            session.beginTransaction();
            List<String> categories = subjectCategoryRepository.getCategories();
            session.getTransaction().commit();
            return categories;
        }
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            studentRepository.setSession(session);
            registrationRepository.setSession(session);
            registrationSemesterRepository.setSession(session);
            registrationCategoryRepository.setSession(session);
            batchRepository.setSession(session);
            subjectCategoryRepository.setSession(session);
            semesterRepository.setSession(session);
            transaction = session.beginTransaction();
            Student student = studentRepository.getEntity(studentRepository.getStudentId(studentDTO.getEmail()));
            if (student == null) {
                student = new Student();
                student.setName(studentDTO.getName());
                student.setPassword(studentDTO.getPassword());
                student.setEmail(studentDTO.getEmail());
                studentRepository.save(student);
            }
            Registration registration = new Registration();
            registration.setRid(studentDTO.getRid());
            registration.setStudent(student);
            Batch batch = batchRepository.getEntity(batchRepository.getBatchId(studentDTO.getBatchName()));
            registration.setBatch(batch);
            boolean saveR = registrationRepository.save(registration);
            if (saveR) {
                RegistrationSemester registrationSemester = new RegistrationSemester();
                registrationSemester.setRegistration(registration);
                Semester semester = semesterRepository.getEntity(semesterRepository.getSemesterid(studentDTO.getSemName()));
                registrationSemester.setSemester(semester);
                boolean saveRs = registrationSemesterRepository.save(registrationSemester);
                if (saveRs) {
                    RegistrationCategory registrationCategory = new RegistrationCategory();
                    SubjectCategory subjectCategory = subjectCategoryRepository.getEntity(subjectCategoryRepository.getSubjectCategoryId(studentDTO.getCatName()));
                    RegistrationCategory_PK registrationCategory_PK = new RegistrationCategory_PK(registrationSemester.getRsid(), subjectCategory.getScid());
                    registrationCategory.setRegistrationCategory_PK(registrationCategory_PK);
                    registrationCategory.setRegistrationSemester(registrationSemester);
                    registrationCategory.setSubjectCategory(subjectCategory);
                    boolean saveRc = registrationCategoryRepository.save(registrationCategory);
                    if (saveRc) {
                        transaction.commit();
                        return true;
                    }
                }
            }
            transaction.rollback();
            return false;
        } catch (ConstraintViolationException ex) {
            //JOptionPane.showMessageDialog(null, "Registration All ready Exist");
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            studentRepository.setSession(session);
            registrationRepository.setSession(session);
            registrationSemesterRepository.setSession(session);
            registrationCategoryRepository.setSession(session);
            batchRepository.setSession(session);
            subjectCategoryRepository.setSession(session);
            semesterRepository.setSession(session);
            transaction = session.beginTransaction();

            Student student = studentRepository.getEntity(studentRepository.getStudentId(studentDTO.getOldEmail()));//
            student.setName(studentDTO.getName());
            student.setPassword(studentDTO.getPassword());
            student.setEmail(studentDTO.getEmail());
            studentRepository.update(student);

            Registration registration = registrationRepository.getEntity(studentDTO.getOldRid());
          //  registration.setRid(studentDTO.getRid());
            registration.setStudent(student);
            Batch batch = batchRepository.getEntity(batchRepository.getBatchId(studentDTO.getOldBatchName()));//
            batch.setBatchName(studentDTO.getBatchName());
            registration.setBatch(batch);
            registrationRepository.update(registration);
//
//            RegistrationSemester registrationSemester = registrationSemesterRepository.getEntity(registrationSemesterRepository.getRegistrationSemesterId(studentDTO.getOldRid()));//
//            registrationSemester.setRegistration(registration);
//            Semester semester = semesterRepository.getEntity(semesterRepository.getSemesterid(studentDTO.getOldSemName()));//
//            registrationSemester.setSemester(semester);
//            registrationSemesterRepository.update(registrationSemester);
//
//            SubjectCategory subjectCategory = subjectCategoryRepository.getEntity(subjectCategoryRepository.getSubjectCategoryId(studentDTO.getOldCatName()));//
//            RegistrationCategory registrationCategory = registrationCategoryRepository.getEntity(new RegistrationCategory_PK(registrationSemester.getRsid(), subjectCategory.getScid()));
//            RegistrationCategory_PK registrationCategory_PK = registrationCategory.getRegistrationCategory_PK();
//            registrationCategory_PK.setRsid(registrationSemester.getRsid());
//            registrationCategory_PK.setScid(subjectCategory.getScid());
//            registrationCategory.setRegistrationCategory_PK(registrationCategory_PK);
//            registrationCategory.setRegistrationSemester(registrationSemester);
//            registrationCategory.setSubjectCategory(subjectCategory);
//            registrationCategoryRepository.update(registrationCategory);

            transaction.commit();
            return true;

        } catch (HibernateException ex) {
            //JOptionPane.showMessageDialog(null, "Registration All ready Exist");
            ex.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
