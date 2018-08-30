/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.service;

import java.rmi.Remote;

/**
 *
 * @author Imalka Gunawardana
 */
public interface ServiceFactory extends Remote {

    public enum ServiceTypes {
        STUDENT, TEACHER, ADMIN
    }

    public SuperService getService(ServiceTypes type) throws Exception;

}
