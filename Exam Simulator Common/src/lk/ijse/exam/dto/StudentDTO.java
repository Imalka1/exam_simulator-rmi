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
public class StudentDTO extends SuperDTO {

    private int stid;
    private String name;
    private String email;
    private String oldEmail;
    private String password;
    private int bid;
    private String batchName;
    private String oldBatchName;
    private String semName;
    private String oldSemName;
    private String rid;
    private String oldRid;
    private String catName;
    private String oldCatName;

    public StudentDTO() {
    }

    public StudentDTO(int stid, String name, String email, String password, String batchName, String semName, String rid) {
        this.stid = stid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.batchName = batchName;
        this.semName = semName;
        this.rid = rid;
    }

    /**
     * @return the stid
     */
    public int getStid() {
        return stid;
    }

    /**
     * @param stid the stid to set
     */
    public void setStid(int stid) {
        this.stid = stid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the batchName
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * @param batchName the batchName to set
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
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

    /**
     * @return the rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * @param rid the rid to set
     */
    public void setRid(String rid) {
        this.rid = rid;
    }

    /**
     * @return the catName
     */
    public String getCatName() {
        return catName;
    }

    /**
     * @param catName the catName to set
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * @return the bid
     */
    public int getBid() {
        return bid;
    }

    /**
     * @param bid the bid to set
     */
    public void setBid(int bid) {
        this.bid = bid;
    }

    /**
     * @return the oldEmail
     */
    public String getOldEmail() {
        return oldEmail;
    }

    /**
     * @param oldEmail the oldEmail to set
     */
    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    /**
     * @return the oldBatchName
     */
    public String getOldBatchName() {
        return oldBatchName;
    }

    /**
     * @param oldBatchName the oldBatchName to set
     */
    public void setOldBatchName(String oldBatchName) {
        this.oldBatchName = oldBatchName;
    }

    /**
     * @return the oldSemName
     */
    public String getOldSemName() {
        return oldSemName;
    }

    /**
     * @param oldSemName the oldSemName to set
     */
    public void setOldSemName(String oldSemName) {
        this.oldSemName = oldSemName;
    }

    /**
     * @return the oldRid
     */
    public String getOldRid() {
        return oldRid;
    }

    /**
     * @param oldRid the oldRid to set
     */
    public void setOldRid(String oldRid) {
        this.oldRid = oldRid;
    }

    /**
     * @return the oldCatName
     */
    public String getOldCatName() {
        return oldCatName;
    }

    /**
     * @param oldCatName the oldCatName to set
     */
    public void setOldCatName(String oldCatName) {
        this.oldCatName = oldCatName;
    }
}
