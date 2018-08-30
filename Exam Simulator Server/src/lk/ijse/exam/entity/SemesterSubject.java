/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Imalka Gunawardana
 */
@Entity
public class SemesterSubject {

    @ManyToOne(cascade = CascadeType.ALL)
    private Semester semester;
    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
    @EmbeddedId
    private SemesterSubject_PK semesterSubject_PK;

    public SemesterSubject() {
    }

    public SemesterSubject(Semester semester, Subject subject, Teacher teacher, SemesterSubject_PK semesterSubject_PK) {
        this.semester = semester;
        this.subject = subject;
        this.teacher = teacher;
        this.semesterSubject_PK = semesterSubject_PK;
    }

    /**
     * @return the semester
     */
    public Semester getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    /**
     * @return the subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * @return the teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * @return the semesterSubject_PK
     */
    public SemesterSubject_PK getSemesterSubject_PK() {
        return semesterSubject_PK;
    }

    /**
     * @param semesterSubject_PK the semesterSubject_PK to set
     */
    public void setSemesterSubject_PK(SemesterSubject_PK semesterSubject_PK) {
        this.semesterSubject_PK = semesterSubject_PK;
    }

    @Override
    public String toString() {
        return "SemesterSubject{" + "semester=" + semester + ", subject=" + subject + ", teacher=" + teacher + ", semesterSubject_PK=" + semesterSubject_PK + '}';
    }
}
