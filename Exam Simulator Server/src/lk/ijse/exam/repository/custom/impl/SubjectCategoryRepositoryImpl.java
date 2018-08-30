/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.exam.dto.SubjectDTO;
import lk.ijse.exam.entity.SubjectCategory;
import lk.ijse.exam.repository.SuperRepositoryImpl;
import lk.ijse.exam.repository.custom.SubjectCategoryRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public class SubjectCategoryRepositoryImpl extends SuperRepositoryImpl<SubjectCategory, Integer> implements SubjectCategoryRepository {

    @Override
    public List<SubjectDTO> loadCategories(String id) throws Exception {
        List<Object[]> list = session.createSQLQuery("select sc.categoryName from registrationSemester regs,registrationcategory rc,subjectcategory sc where regs.rsid=rc.registrationSemester_rsid && sc.scid=rc.subjectCategory_scid && regs.registration_rid='" + id + "'").list();
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
    public List<String> getCategories() throws Exception {
        List<Object[]> list = session.createSQLQuery("select categoryName from subjectcategory").list();
        List<String> subjectDTOs = new ArrayList<>();
        if (list.size() > 0) {
            for (Object obj : list) {
                subjectDTOs.add(obj.toString());
            }
        }
        return subjectDTOs;
    }

    @Override
    public int getSubjectCategoryId(String name) throws Exception {
        List<Object[]> list = session.createSQLQuery("select scid from subjectcategory where categoryName='" + name + "'").list();
        int id = 0;
        if (list.size() > 0) {
            for (Object obj : list) {
                id = Integer.parseInt(obj.toString());
            }
        }
        return id;
    }
}
