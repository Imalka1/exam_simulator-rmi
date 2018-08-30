/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.reservation.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lk.ijse.exam.reservation.Reservation;
import lk.ijse.exam.service.SuperService;

/**
 *
 * @author Imalka Gunawardana
 */
public class ReservationImpl implements Reservation {

    private Map<String, List<String>> resBook;
    // private List<String> resBook = new LinkedList<>();

    public ReservationImpl() {
        resBook = new HashMap<>();
        resBook.put("Student", new LinkedList<String>());
        resBook.put("Teacher", new LinkedList<String>());
    }

    public boolean reserve(String key, String value) {
        List<String> get = resBook.get(key);
        if (get.contains(value)) {
            return false;
        } else {
            get.add(value);
            return true;
        }
    }

    public boolean release(String key, String value) {
        List<String> get = resBook.get(key);
        if (get.contains(value)) {
            get.remove(value);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Map<String, List<String>> getReservations() throws Exception {
        return resBook;
    }

}
