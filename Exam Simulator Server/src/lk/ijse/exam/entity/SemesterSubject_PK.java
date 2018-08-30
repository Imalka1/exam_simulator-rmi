/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Imalka Gunawardana
 */
@Embeddable
public class SemesterSubject_PK implements Serializable {

    private int semid;
    private String subid;
    private int tid;

    public SemesterSubject_PK() {
    }

    public SemesterSubject_PK(int semid, String subid, int tid) {
        this.semid = semid;
        this.subid = subid;
        this.tid = tid;
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
     * @return the subid
     */
    public String getSubid() {
        return subid;
    }

    /**
     * @param subid the subid to set
     */
    public void setSubid(String subid) {
        this.subid = subid;
    }

    /**
     * @return the tid
     */
    public int getTid() {
        return tid;
    }

    /**
     * @param tid the tid to set
     */
    public void setTid(int tid) {
        this.tid = tid;
    }
}
