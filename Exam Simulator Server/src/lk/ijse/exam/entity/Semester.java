/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Imalka Gunawardana
 */
@Entity
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int semid;
    private String semName;

    public Semester() {
    }

    public Semester(int semid, String semName) {
        this.semid = semid;
        this.semName = semName;
    }

    /**
     * @return the semid
     */
    public int getSemid() {
        return semid;
    }

    /**
     * @param semid the semid to set
     */
    public void setSemid(int semid) {
        this.semid = semid;
    }

    /**
     * @return the semName
     */
    public String getSemName() {
        return semName;
    }

    /**
     * @param semName the semName to set
     */
    public void setSemName(String semName) {
        this.semName = semName;
    }

    @Override
    public String toString() {
        return "Semester{" + "semid=" + semid + ", semName=" + semName + '}';
    }
}
