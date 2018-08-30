/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom;

import lk.ijse.exam.entity.RegistrationSemester;
import lk.ijse.exam.repository.SuperRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public interface RegistrationSemesterRepository extends SuperRepository<RegistrationSemester, Integer> {

    int getRegistrationSemesterId(String name) throws Exception;
}
