/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom;

import java.util.List;
import lk.ijse.exam.entity.Batch;
import lk.ijse.exam.repository.SuperRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public interface BatchRepository extends SuperRepository<Batch, Integer> {

    List<String> getBatches() throws Exception;

    int getBatchId(String name) throws Exception;
}
