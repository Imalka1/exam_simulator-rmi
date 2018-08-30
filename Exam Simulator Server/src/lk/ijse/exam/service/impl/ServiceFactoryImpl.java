/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import lk.ijse.exam.service.ServiceFactory;
import lk.ijse.exam.service.SuperService;
import lk.ijse.exam.service.custom.impl.AdminServiceImpl;
import lk.ijse.exam.service.custom.impl.StudentServiceImpl;
import lk.ijse.exam.service.custom.impl.TeacherServiceImpl;

/**
 *
 * @author Imalka Gunawardana
 */
public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {

    public static ServiceFactory serviceFactory;

    private ServiceFactoryImpl() throws RemoteException {

    }

    public static ServiceFactory getInstance() throws RemoteException {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    @Override
    public SuperService getService(ServiceTypes type) throws Exception {
        switch (type) {
            case STUDENT:
                return new StudentServiceImpl();
            case TEACHER:
                return new TeacherServiceImpl();
            case ADMIN:
                return new AdminServiceImpl();
            default:
                return null;
        }
    }
}
