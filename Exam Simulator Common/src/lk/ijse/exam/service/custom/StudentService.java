/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.service.custom;

import java.util.List;
import lk.ijse.exam.dto.PaperDTO;
import lk.ijse.exam.dto.SemesterSubjectDTO;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.dto.SubjectDTO;
import lk.ijse.exam.dto.TeacherDTO;
import lk.ijse.exam.service.SuperService;

/**
 *
 * @author Imalka Gunawardana
 */
public interface StudentService extends SuperService {

    int chkLogin(StudentDTO studentDTO) throws Exception;

    StudentDTO getStudentDetails(int id) throws Exception;

    List<String> getSemesters() throws Exception;

    List<TeacherDTO> loadTeachers(String id) throws Exception;

    List<SubjectDTO> loadSubjects(SemesterSubjectDTO semesterSubjectDTO) throws Exception;

    List<SubjectDTO> loadCategories(String id) throws Exception;
    
    List<String> getModelQuestionPapers(PaperDTO paperDTO) throws Exception;
    
    PaperDTO generateQuestionsByStudent(PaperDTO paperDTO) throws Exception;
}
