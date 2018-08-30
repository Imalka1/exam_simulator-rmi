/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom;

import java.util.List;
import lk.ijse.exam.dto.TeacherDTO;
import lk.ijse.exam.entity.Teacher;
import lk.ijse.exam.repository.SuperRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public interface TeacherRepository extends SuperRepository<Teacher, Integer> {

    TeacherDTO chkLogin(Teacher teacher) throws Exception;

    TeacherDTO getTeacherDetails(int id) throws Exception;

    List<TeacherDTO> loadTeachers(String id) throws Exception;

    int getTeacherId(String name) throws Exception;

    String getTeacherName(int id) throws Exception;
}
