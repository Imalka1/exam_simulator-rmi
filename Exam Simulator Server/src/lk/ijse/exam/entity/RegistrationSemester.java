/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Imalka Gunawardana
 */
@Entity
public class RegistrationSemester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rsid;
    @ManyToOne(cascade = CascadeType.ALL)
    private Registration registration;
    @ManyToOne(cascade = CascadeType.ALL)
    private Semester semester;

    public RegistrationSemester() {
    }

    public RegistrationSemester(int rsid, Registration registration, Semester semester) {
        this.rsid = rsid;
        this.registration = registration;
        this.semester = semester;
    }

    /**
     * @return the rsid
     */
    public int getRsid() {
        return rsid;
    }

    /**
     * @param rsid the rsid to set
     */
    public void setRsid(int rsid) {
        this.rsid = rsid;
    }

    /**
     * @return the registration
     */
    public Registration getRegistration() {
        return registration;
    }

    /**
     * @param registration the registration to set
     */
    public void setRegistration(Registration registration) {
        this.registration = registration;
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

    @Override
    public String toString() {
        return "RegistrationSemester{" + "rsid=" + rsid + ", registration=" + registration + ", semester=" + semester + '}';
    }
}
