/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.proxy;

import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.exam.service.ServiceFactory;
import lk.ijse.exam.service.SuperService;
import lk.ijse.exam.service.custom.StudentService;
import lk.ijse.exam.service.custom.TeacherService;

/**
 *
 * @author Imalka Gunawardana
 */
public class ProxyHandler implements ServiceFactory {

    private static ProxyHandler proxyHandler;
    private ServiceFactory serviceFactory;
    private StudentService studentService;
    private TeacherService teacherService;

    private ProxyHandler() {
        try {
            Properties properties = new Properties();
            File file = new File("settings/ConnectionProp.properties");
            FileReader inputStream = new FileReader(file.getAbsolutePath());
            properties.load(inputStream);
            serviceFactory = (ServiceFactory) Naming.lookup("rmi://" + properties.getProperty("ip") + ":" + properties.getProperty("port") + "/" + properties.getProperty("server") + "");
            studentService = (StudentService) serviceFactory.getService(ServiceTypes.STUDENT);
            teacherService = (TeacherService) serviceFactory.getService(ServiceTypes.TEACHER);
        } catch (NotBoundException ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProxyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ProxyHandler getInstance() {
        if (proxyHandler == null) {
            proxyHandler = new ProxyHandler();
        }
        return proxyHandler;
    }

    @Override
    public SuperService getService(ServiceTypes type) throws Exception {
        switch (type) {
            case STUDENT:
                return studentService;
            case TEACHER:
                return teacherService;
            default:
                return null;
        }
    }
}
