/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom.impl;

import java.util.List;
import lk.ijse.exam.entity.RegistrationCategory;
import lk.ijse.exam.entity.RegistrationCategory_PK;
import lk.ijse.exam.repository.SuperRepositoryImpl;
import lk.ijse.exam.repository.custom.RegistrationCategoryRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public class RegistrationCategoryRepositoryImpl extends SuperRepositoryImpl<RegistrationCategory, RegistrationCategory_PK> implements RegistrationCategoryRepository {

    @Override
    public int getRegistrationCategoryId(String name) throws Exception {
        List<Object[]> list = session.createSQLQuery("select rsid from batch where batchName='" + name + "'").list();
        int id = 0;
        if (list.size() > 0) {
            for (Object obj : list) {
                id = Integer.parseInt(obj.toString());
            }
        }
        return id;
    }

}
