/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom;

import java.util.List;
import lk.ijse.exam.dto.SubjectDTO;
import lk.ijse.exam.entity.SubjectCategory;
import lk.ijse.exam.repository.SuperRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public interface SubjectCategoryRepository extends SuperRepository<SubjectCategory, Integer> {

    List<SubjectDTO> loadCategories(String id) throws Exception;

    List<String> getCategories() throws Exception;

    int getSubjectCategoryId(String name) throws Exception;
}
