package lk.ijse.exam.dto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Imalka Gunawardana
 */
public class SemesterDTO extends SuperDTO {

    private int semid;
    private String semName;

    public SemesterDTO() {
    }

    public SemesterDTO(int semid, String semName) {
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

}
