/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.service.custom;

import java.util.List;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.reservation.Reservation;
import lk.ijse.exam.service.SuperService;

/**
 *
 * @author Imalka Gunawardana
 */
public interface AdminService extends SuperService, Reservation {
    
    boolean saveStudent(StudentDTO studentDTO) throws Exception;
    
    boolean updateStudent(StudentDTO studentDTO) throws Exception;
    
    boolean deleteStudent(StudentDTO studentDTO) throws Exception;

    List<StudentDTO> getAllStudents() throws Exception;

    List<String> getBatches() throws Exception;
    
    List<String> getSemesters() throws Exception;
    
    List<String> getCategories() throws Exception;
}
