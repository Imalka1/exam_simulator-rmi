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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stid;
    private String name;
    private String email;
    private String password;

    public Student() {
    }

    public Student(int stid, String name, String email, String password) {
        this.stid = stid;
        this.name = name;
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "Student{" + "stid=" + stid + ", name=" + name + ", email=" + email + ", password=" + password + '}';
    }
}
