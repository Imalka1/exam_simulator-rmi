/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.observer;

import java.rmi.Remote;

/**
 *
 * @author Imalka Gunawardana
 */
public interface Observer extends Remote {

    void updateObservers(int value) throws Exception;

    String[] testsExamsStatus() throws Exception;
}
