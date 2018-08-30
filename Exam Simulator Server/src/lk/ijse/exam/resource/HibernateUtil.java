/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.resource;

import java.io.File;
import lk.ijse.exam.entity.Batch;
import lk.ijse.exam.entity.Registration;
import lk.ijse.exam.entity.RegistrationCategory;
import lk.ijse.exam.entity.RegistrationSemester;
import lk.ijse.exam.entity.Semester;
import lk.ijse.exam.entity.SemesterSubject;
import lk.ijse.exam.entity.Student;
import lk.ijse.exam.entity.Subject;
import lk.ijse.exam.entity.SubjectCategory;
import lk.ijse.exam.entity.Teacher;
import lk.ijse.exam.entity.TeacherCategory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Imalka Gunawardana
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    private static StandardServiceRegistry registry;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            File hibernateProperties = new File("settings/hibernate.properties");
            registry = new StandardServiceRegistryBuilder().loadProperties(hibernateProperties).build();
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Batch.class)
                    .addAnnotatedClass(Registration.class)
                    .addAnnotatedClass(Semester.class)
                    .addAnnotatedClass(Subject.class)
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(SemesterSubject.class)
                    .addAnnotatedClass(RegistrationSemester.class)
                    .addAnnotatedClass(SubjectCategory.class)
                    .addAnnotatedClass(RegistrationCategory.class)
                    .addAnnotatedClass(TeacherCategory.class)
                    .buildMetadata().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            StandardServiceRegistryBuilder.destroy(registry);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
