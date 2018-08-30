/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.business;

import lk.ijse.exam.business.custom.impl.AdminBOImpl;
import lk.ijse.exam.business.custom.impl.StudentBOImpl;
import lk.ijse.exam.business.custom.impl.TeacherBOImpl;

/**
 *
 * @author Imalka Gunawardana
 */
public class BOFactory {

    public enum BOTypes {
        STUDENT, TEACHER, ADMIN
    }

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes type) {
        switch (type) {
            case STUDENT:
                return new StudentBOImpl();
            case TEACHER:
                return new TeacherBOImpl();
            case ADMIN:
                return new AdminBOImpl();
            default:
                return null;
        }
    }
}
