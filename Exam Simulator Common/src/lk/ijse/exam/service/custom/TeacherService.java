/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.service.custom;

import java.io.File;
import java.util.List;
import lk.ijse.exam.dto.PaperDTO;
import lk.ijse.exam.dto.SemesterSubjectDTO;
import lk.ijse.exam.dto.TeacherDTO;
import lk.ijse.exam.service.SuperService;

/**
 *
 * @author Imalka Gunawardana
 */
public interface TeacherService extends SuperService {

    TeacherDTO chkLogin(TeacherDTO teacherDTO) throws Exception;

    TeacherDTO getTeacherDetails(int id) throws Exception;

    List<SemesterSubjectDTO> loadSubjects(int id) throws Exception;

    String getTeacherName(int id) throws Exception;

    List<String> getQuestionPapers(PaperDTO paperDTO) throws Exception;

    int getQuestionsCount(PaperDTO paperDTO) throws Exception;

    File getFilePath(String name) throws Exception;
}
