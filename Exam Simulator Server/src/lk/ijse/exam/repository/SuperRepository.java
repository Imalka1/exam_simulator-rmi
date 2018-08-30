/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.repository;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Imalka Gunawardana
 */
public interface SuperRepository<T, ID> {

    void setSession(Session session) throws Exception;

    //Session getSession() throws Exception;

    boolean save(T t) throws Exception;

    void delete(T t) throws Exception;

    void update(T t) throws Exception;

    T getEntity(ID id) throws Exception;

    List<T> findAll() throws Exception;
}
