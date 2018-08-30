/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom.impl;

import java.util.List;
import lk.ijse.exam.entity.RegistrationSemester;
import lk.ijse.exam.repository.SuperRepositoryImpl;
import lk.ijse.exam.repository.custom.RegistrationSemesterRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public class RegistrationSemesterRepositoryImpl extends SuperRepositoryImpl<RegistrationSemester, Integer> implements RegistrationSemesterRepository {

    @Override
    public int getRegistrationSemesterId(String name) throws Exception {
        List<Object[]> list = session.createSQLQuery("select rsid from RegistrationSemester where registration_rid='" + name + "'").list();
        int id = 0;
        if (list.size() > 0) {
            for (Object obj : list) {
                id = Integer.parseInt(obj.toString());
            }
        }
        return id;
    }

}
