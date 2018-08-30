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
public class RegistrationSemesterDTO {

    private int rsid;
    private String rid;
    private int semid;

    public RegistrationSemesterDTO() {
    }

    public RegistrationSemesterDTO(int rsid, String rid, int semid) {
        this.rsid = rsid;
        this.rid = rid;
        this.semid = semid;
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
}
