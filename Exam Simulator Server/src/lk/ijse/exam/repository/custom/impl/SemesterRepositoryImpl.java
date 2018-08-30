/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.exam.entity.Semester;
import lk.ijse.exam.repository.SuperRepositoryImpl;
import lk.ijse.exam.repository.custom.SemesterRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public class SemesterRepositoryImpl extends SuperRepositoryImpl<Semester, Integer> implements SemesterRepository {

    @Override
    public List<String> getSemesters() throws Exception {
        List<Object[]> list = session.createSQLQuery("select semName from semester").list();
        List<String> semesterDTOs = new ArrayList<>();
        if (list.size() > 0) {
            for (Object obj : list) {
                semesterDTOs.add(obj.toString());
            }
        }
        return semesterDTOs;
    }

    @Override
    public int getSemesterid(String name) throws Exception {
        List<Object[]> list = session.createSQLQuery("select semid from semester where semName='" + name + "'").list();
        int id = 0;
        if (list.size() > 0) {
            for (Object obj : list) {
                id = Integer.parseInt(obj.toString());
            }
        }
        return id;
    }

}
