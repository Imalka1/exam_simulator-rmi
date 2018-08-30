/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.exam.entity.Batch;
import lk.ijse.exam.repository.SuperRepositoryImpl;
import lk.ijse.exam.repository.custom.BatchRepository;

/**
 *
 * @author Imalka Gunawardana
 */
public class BatchRepositoryImpl extends SuperRepositoryImpl<Batch, Integer> implements BatchRepository {

    @Override
    public List<String> getBatches() throws Exception {
        List<Object[]> list = session.createSQLQuery("select batchName from batch").list();
        List<String> batches = new ArrayList<>();
        if (list.size() > 0) {
            for (Object obj : list) {
                batches.add(obj.toString());
            }
        }
        return batches;
    }

    @Override
    public int getBatchId(String name) throws Exception {
        List<Object[]> list = session.createSQLQuery("select bid from batch where batchName='" + name + "'").list();
        int id = 0;
        if (list.size() > 0) {
            for (Object obj : list) {
                id = Integer.parseInt(obj.toString());
            }
        }
        return id;
    }
}
