/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.dto;

/**
 *
 * @author Imalka Gunawardana
 */
public class SubjectDTO extends SuperDTO {

    private String subid;
    private String subName;

    public SubjectDTO() {
    }

    public SubjectDTO(String subid, String subName) {
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
}
