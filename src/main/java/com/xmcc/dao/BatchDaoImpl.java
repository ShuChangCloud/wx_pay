package com.xmcc.dao;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-21 17:19
 */


public class BatchDaoImpl<T> implements BatchDao<T> {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void batchInsert(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            entityManager.persist(list.get(i));
            if (i%10==0 || i==list.size()-1) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}
