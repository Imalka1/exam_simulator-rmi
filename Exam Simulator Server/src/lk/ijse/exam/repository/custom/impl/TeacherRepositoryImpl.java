/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.exam.dto.TeacherDTO;
import lk.ijse.exam.entity.Teacher;
import lk.ijse.exam.repository.SuperRepositoryImpl;
import lk.ijse.exam.repository.custom.TeacherRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public class TeacherRepositoryImpl extends SuperRepositoryImpl<Teacher, Integer> implements TeacherRepository {
    
    @Override
    public TeacherDTO chkLogin(Teacher teacher) throws Exception {
        List<Object[]> list = session.createSQLQuery("select tid,type from teacher where name='" + teacher.getName() + "' && password='" + teacher.getPassword() + "'").list();
        if (list.size() > 0) {
            TeacherDTO teacherDTO = new TeacherDTO();
            Object obj[] = list.get(0);
            teacherDTO.setTid(Integer.parseInt(obj[0].toString()));
            teacherDTO.setType(Integer.parseInt(obj[1].toString()));
            return teacherDTO;
        } else {
            return null;
        }
    }
    
    @Override
    public TeacherDTO getTeacherDetails(int id) throws Exception {
        List<Object[]> list = session.createSQLQuery("select name,email from teacher where tid=" + id + "").list();
        if (list.size() > 0) {
            TeacherDTO teacherDTO = new TeacherDTO();
            Object obj[] = list.get(0);
            teacherDTO.setName(obj[0].toString());
            teacherDTO.setEmail(obj[1].toString());
            return teacherDTO;
        } else {
            return null;
        }
    }
    
    @Override
    public List<TeacherDTO> loadTeachers(String id) throws Exception {
        List<Object[]> list = session.createSQLQuery("select name from teacher t,teachercategory tc,subjectcategory sc where t.tid=tc.teacher_tid && sc.scid=tc.subjectCategory_scid && sc.categoryName='" + id + "'").list();
        List<TeacherDTO> teacherDTOs = null;
        if (list.size() > 0) {
            teacherDTOs = new ArrayList<>();
            for (Object obj : list) {
                TeacherDTO teacherDTO = new TeacherDTO();
                teacherDTO.setName(obj.toString());
                teacherDTOs.add(teacherDTO);
            }
        }
        return teacherDTOs;
    }
    
    @Override
    public int getTeacherId(String name) throws Exception {
        List list = session.createSQLQuery("select tid from teacher where name='" + name + "'").list();
        if (list.size() > 0) {
            return (int) list.get(0);
        } else {
            return 0;
        }
    }
    
    @Override
    public String getTeacherName(int id) throws Exception {
        List list = session.createSQLQuery("select name from teacher where tid=" + id + "").list();
        if (list.size() > 0) {
            return (String) list.get(0);
        } else {
            return null;
        }
    }
}
