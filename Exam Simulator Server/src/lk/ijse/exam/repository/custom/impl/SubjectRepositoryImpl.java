/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.exam.dto.SemesterSubjectDTO;
import lk.ijse.exam.dto.SubjectDTO;
import lk.ijse.exam.entity.SemesterSubject;
import lk.ijse.exam.observer.Subject;
import lk.ijse.exam.repository.SuperRepositoryImpl;
import lk.ijse.exam.repository.custom.SubjectRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public class SubjectRepositoryImpl extends SuperRepositoryImpl<Subject, Integer> implements SubjectRepository {

    @Override
    public List<SubjectDTO> loadSubjects(SemesterSubject semesterSubject) throws Exception {
        List<Object[]> list = session.createSQLQuery("select sub.subName from semestersubject semsub,subject sub,semester sem,teacher t where t.tid=semsub.teacher_tid && sub.subid=semsub.subject_subid && sem.semid=semsub.semester_semid && t.name='" + semesterSubject.getTeacher().getName() + "' && semname='" + semesterSubject.getSemester().getSemName() + "'").list();
        List<SubjectDTO> subjectDTOs = null;
        if (list.size() > 0) {
            subjectDTOs = new ArrayList<>();
            for (Object obj : list) {
                SubjectDTO subjectDTO = new SubjectDTO();
                subjectDTO.setSubName(obj.toString());
                subjectDTOs.add(subjectDTO);
            }
        }
        return subjectDTOs;
    }

    @Override
    public List<SemesterSubjectDTO> loadSubjects(int id) throws Exception {
        List<Object[][]> list = session.createSQLQuery("select subName,semName from subject s,semestersubject semsub,teacher t,semester sem where s.subid=semsub.subject_subid && t.tid=semsub.teacher_tid && sem.semid=semsub.semester_semid && t.tid=" + id + " order by 1").list();
        List<SemesterSubjectDTO> semesterSubjectDTOs = null;
        if (list.size() > 0) {
            semesterSubjectDTOs = new ArrayList<>();
            for (Object obj[] : list) {
                SemesterSubjectDTO ssdto = new SemesterSubjectDTO();
                ssdto.setSemesterName(obj[1].toString());
                ssdto.setSubjectName(obj[0].toString());
                semesterSubjectDTOs.add(ssdto);
            }
        }
        return semesterSubjectDTOs;
    }
}
