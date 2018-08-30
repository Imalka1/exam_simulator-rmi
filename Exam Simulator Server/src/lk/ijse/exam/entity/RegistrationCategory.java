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
public class RegistrationCategory {

    @ManyToOne(cascade = CascadeType.ALL)
    private RegistrationSemester registrationSemester;
    @ManyToOne(cascade = CascadeType.ALL)
    private SubjectCategory subjectCategory;
    @EmbeddedId
    private RegistrationCategory_PK registrationCategory_PK;

    public RegistrationCategory() {
    }

    public RegistrationCategory(RegistrationSemester registrationSemester, SubjectCategory subjectCategory, RegistrationCategory_PK registrationCategory_PK) {
        this.registrationSemester = registrationSemester;
        this.subjectCategory = subjectCategory;
        this.registrationCategory_PK = registrationCategory_PK;
    }

    /**
     * @return the registrationSemester
     */
    public RegistrationSemester getRegistrationSemester() {
        return registrationSemester;
    }

    /**
     * @param registrationSemester the registrationSemester to set
     */
    public void setRegistrationSemester(RegistrationSemester registrationSemester) {
        this.registrationSemester = registrationSemester;
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
     * @return the registrationCategory_PK
     */
    public RegistrationCategory_PK getRegistrationCategory_PK() {
        return registrationCategory_PK;
    }

    /**
     * @param registrationCategory_PK the registrationCategory_PK to set
     */
    public void setRegistrationCategory_PK(RegistrationCategory_PK registrationCategory_PK) {
        this.registrationCategory_PK = registrationCategory_PK;
    }

    @Override
    public String toString() {
        return "RegistrationCategory{" + "registrationSemester=" + registrationSemester + ", subjectCategory=" + subjectCategory + ", registrationCategory_PK=" + registrationCategory_PK + '}';
    }
}
