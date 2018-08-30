/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.reservation;

import java.rmi.Remote;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Imalka Gunawardana
 */
public interface Reservation extends Remote {

    boolean reserve(String key, String value) throws Exception;

    boolean release(String key, String value) throws Exception;

    Map<String, List<String>> getReservations() throws Exception;

}
