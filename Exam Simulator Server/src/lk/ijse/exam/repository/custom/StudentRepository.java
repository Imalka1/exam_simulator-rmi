/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom;

import java.util.List;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.entity.Student;
import lk.ijse.exam.repository.SuperRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public interface StudentRepository extends SuperRepository<Student, Integer> {

    int chkLogin(Student student) throws Exception;

    StudentDTO getStudentDetails(int id) throws Exception;

    List<StudentDTO> getAllStudents() throws Exception;
    
    int getStudentId(String name) throws Exception;
}
