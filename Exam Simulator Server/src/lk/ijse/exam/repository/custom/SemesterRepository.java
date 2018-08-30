/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom;

import java.util.List;
import lk.ijse.exam.entity.Semester;
import lk.ijse.exam.repository.SuperRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public interface SemesterRepository extends SuperRepository<Semester, Integer> {

    int getSemesterid(String name) throws Exception;

    List<String> getSemesters() throws Exception;
}
