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
public class TeacherCategory_PK implements Serializable {

    private int tid;
    private int scid;

    public TeacherCategory_PK() {
    }

    public TeacherCategory_PK(int tid, int scid) {
        this.tid = tid;
        this.scid = scid;
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

    /**
     * @return the scid
     */
    public int getScid() {
        return scid;
    }

    /**
     * @param scid the scid to set
     */
    public void setScid(int scid) {
        this.scid = scid;
    }
}
