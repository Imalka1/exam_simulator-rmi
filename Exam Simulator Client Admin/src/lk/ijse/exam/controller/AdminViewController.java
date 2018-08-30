/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.controller;

import lk.ijse.exam.observer.Subject;
import lk.ijse.exam.proxy.ProxyHandler;
import lk.ijse.exam.reservation.Reservation;
import lk.ijse.exam.service.ServiceFactory;

/**
 *
 * @author Imalka Gunawardana
 */
public class AdminViewController {

    public static Subject getAdminSubject() throws Exception {
        return (Subject) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
    }
    
    public static Reservation getAdminReservation() throws Exception {
        return (Reservation) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ADMIN);
    }
}
