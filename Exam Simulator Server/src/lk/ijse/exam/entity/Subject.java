/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Imalka Gunawardana
 */
@Entity
public class Subject {

    @Id
    private String subid;
    private String subName;

    public Subject() {
    }

    public Subject(String subid, String subName) {
        this.subid = subid;
        this.subName = subName;
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
     * @return the subName
     */
    public String getSubName() {
        return subName;
    }

    /**
     * @param subName the subName to set
     */
    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Override
    public String toString() {
        return "Subject{" + "subid=" + subid + ", subName=" + subName + '}';
    }
}
