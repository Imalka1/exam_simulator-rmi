/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom;

import lk.ijse.exam.entity.RegistrationCategory;
import lk.ijse.exam.entity.RegistrationCategory_PK;
import lk.ijse.exam.repository.SuperRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public interface RegistrationCategoryRepository extends SuperRepository<RegistrationCategory, RegistrationCategory_PK> {

    int getRegistrationCategoryId(String name) throws Exception;
}
