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
public class BatchDTO {

    private int bid;
    private String batchName;

    public BatchDTO() {
    }

    public BatchDTO(int bid, String batchName) {
        this.bid = bid;
        this.batchName = batchName;
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

}
