/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom;

import java.util.List;
import lk.ijse.exam.dto.SemesterSubjectDTO;
import lk.ijse.exam.dto.SubjectDTO;
import lk.ijse.exam.entity.SemesterSubject;
import lk.ijse.exam.observer.Subject;
import lk.ijse.exam.repository.SuperRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public interface SubjectRepository extends SuperRepository<Subject, Integer> {

    List<SubjectDTO> loadSubjects(SemesterSubject semesterSubject) throws Exception;

    List<SemesterSubjectDTO> loadSubjects(int id) throws Exception;
}
