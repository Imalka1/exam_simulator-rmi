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
public class RegistrationCategory_PK implements Serializable {

    private int rsid;
    private int scid;

    public RegistrationCategory_PK() {
    }

    public RegistrationCategory_PK(int rsid, int scid) {
        this.rsid = rsid;
        this.scid = scid;
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
