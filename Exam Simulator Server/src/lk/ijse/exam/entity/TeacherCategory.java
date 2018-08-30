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
public class TeacherCategory {

    @ManyToOne(cascade = CascadeType.ALL)
    private SubjectCategory subjectCategory;
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
    @EmbeddedId
    private TeacherCategory_PK teacherCategory_PK;

    public TeacherCategory() {
    }

    public TeacherCategory(SubjectCategory subjectCategory, Teacher teacher, TeacherCategory_PK teacherCategory_PK) {
        this.subjectCategory = subjectCategory;
        this.teacher = teacher;
        this.teacherCategory_PK = teacherCategory_PK;
    }

    /**
     * @return the subjectCategory
     */
    public SubjectCategory getSubjectCategory() {
        return subjectCategory;
    }

    /**
     * @param subjectCategory the subjectCategory to set
     */
    public void setSubjectCategory(SubjectCategory subjectCategory) {
        this.subjectCategory = subjectCategory;
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
     * @return the teacherCategory_PK
     */
    public TeacherCategory_PK getTeacherCategory_PK() {
        return teacherCategory_PK;
    }

    /**
     * @param teacherCategory_PK the teacherCategory_PK to set
     */
    public void setTeacherCategory_PK(TeacherCategory_PK teacherCategory_PK) {
        this.teacherCategory_PK = teacherCategory_PK;
    }

    @Override
    public String toString() {
        return "TeacherCategory{" + "subjectCategory=" + subjectCategory + ", teacher=" + teacher + ", teacherCategory_PK=" + teacherCategory_PK + '}';
    }
}
