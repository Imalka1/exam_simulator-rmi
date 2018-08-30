/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.exam.dto.StudentDTO;
import lk.ijse.exam.entity.Student;
import lk.ijse.exam.repository.SuperRepositoryImpl;
import lk.ijse.exam.repository.custom.StudentRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public class StudentRepositoryImpl extends SuperRepositoryImpl<Student, Integer> implements StudentRepository {

    @Override
    public int chkLogin(Student student) throws Exception {
        List list = session.createSQLQuery("select stid from student where name='" + student.getName() + "' && password='" + student.getPassword() + "'").list();
        if (list.size() > 0) {
            return (int) list.get(0);
        } else {
            return 0;
        }
    }

    @Override
    public StudentDTO getStudentDetails(int id) throws Exception {
        List<Object[]> list = session.createSQLQuery("select s.name,s.email,b.batchName,sem.semName,r.rid from student s,batch b,registration r,registrationSemester regs,semester sem where s.stid=r.student_stid && b.bid=r.batch_bid && r.rid=regs.registration_rid && regs.semester_semid=sem.semId && s.stid=" + id + "").list();
        if (list.size() > 0) {
            StudentDTO studentDTO = new StudentDTO();
            Object obj[] = list.get(0);
            studentDTO.setName(obj[0].toString());
            studentDTO.setEmail(obj[1].toString());
            studentDTO.setBatchName(obj[2].toString());
            studentDTO.setSemName(obj[3].toString());
            studentDTO.setRid(obj[4].toString());
            return studentDTO;
        } else {
            return null;
        }
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        List<Object[][]> list = session.createSQLQuery("select rid,name,email,batchName,semName,categoryName from student s,batch b,semester sem,registration r,registrationSemester rs,RegistrationCategory rc,SubjectCategory sc where s.stid=r.student_stid && b.bid=r.batch_bid && r.rid=rs.registration_rid && rs.semester_semid=sem.semid && rs.rsid=rc.registrationSemester_rsid && sc.scid=rc.subjectCategory_scid").list();
        List<StudentDTO> studentDTOs = null;
        if (list.size() > 0) {
            studentDTOs = new ArrayList<>();
            for (Object obj[] : list) {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setRid(obj[0].toString());
                studentDTO.setName(obj[1].toString());
                studentDTO.setEmail(obj[2].toString());
                studentDTO.setBatchName(obj[3].toString());
                studentDTO.setSemName(obj[4].toString());
                studentDTO.setCatName(obj[5].toString());
                studentDTOs.add(studentDTO);
            }
        }
        return studentDTOs;
    }

    @Override
    public int getStudentId(String name) throws Exception {
        List<Object[]> list = session.createSQLQuery("select stid from student where email='" + name + "'").list();
        int id = 0;
        if (list.size() > 0) {
            for (Object obj : list) {
                id = Integer.parseInt(obj.toString());
            }
        }
        return id;
    }
}
