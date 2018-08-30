/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.observer;

import java.rmi.Remote;
import java.util.ArrayList;

/**
 *
 * @author Imalka Gunawardana
 */
public interface Subject extends Remote {

    void registerObserver(Observer observer) throws Exception;

    void unregisterObserver(Observer observer) throws Exception;

    void notifyObservers(int value) throws Exception;

    ArrayList<Observer> getObservers() throws Exception;
}
