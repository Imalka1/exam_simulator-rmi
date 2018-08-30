/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository;

import lk.ijse.exam.repository.custom.impl.BatchRepositoryImpl;
import lk.ijse.exam.repository.custom.impl.RegistrationCategoryRepositoryImpl;
import lk.ijse.exam.repository.custom.impl.RegistrationRepositoryImpl;
import lk.ijse.exam.repository.custom.impl.RegistrationSemesterRepositoryImpl;
import lk.ijse.exam.repository.custom.impl.SemesterRepositoryImpl;
import lk.ijse.exam.repository.custom.impl.SemesterSubjectRepositoryImpl;
import lk.ijse.exam.repository.custom.impl.StudentRepositoryImpl;
import lk.ijse.exam.repository.custom.impl.SubjectCategoryRepositoryImpl;
import lk.ijse.exam.repository.custom.impl.SubjectRepositoryImpl;
import lk.ijse.exam.repository.custom.impl.TeacherRepositoryImpl;

/**
 *
 * @author Imalka Gunawardana
 */
public class RepositoryFactory {

    public enum RepositoryTypes {
        STUDENT, SEMESTER, TEACHER, SUBJECT, SUBJECTCATEGORY, SEMESTERSUBJECT, BATCH, REGISTRATION, REGISTRATIONSEMESTER, REGISTRATIONCATEGORY
    }

    public static RepositoryFactory repositoryFactory;

    private RepositoryFactory() {

    }

    public static RepositoryFactory getInstance() {
        if (repositoryFactory == null) {
            repositoryFactory = new RepositoryFactory();
        }
        return repositoryFactory;
    }

    public SuperRepository getRepository(RepositoryTypes type) {
        switch (type) {
            case STUDENT:
                return new StudentRepositoryImpl();
            case SEMESTER:
                return new SemesterRepositoryImpl();
            case TEACHER:
                return new TeacherRepositoryImpl();
            case SUBJECT:
                return new SubjectRepositoryImpl();
            case SUBJECTCATEGORY:
                return new SubjectCategoryRepositoryImpl();
            case SEMESTERSUBJECT:
                return new SemesterSubjectRepositoryImpl();
            case BATCH:
                return new BatchRepositoryImpl();
            case REGISTRATION:
                return new RegistrationRepositoryImpl();
            case REGISTRATIONSEMESTER:
                return new RegistrationSemesterRepositoryImpl();
            case REGISTRATIONCATEGORY:
                return new RegistrationCategoryRepositoryImpl();
            default:
                return null;
        }
    }
}
