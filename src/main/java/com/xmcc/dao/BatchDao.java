package com.xmcc.dao;


import java.util.List;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-21 17:19
 */
public interface BatchDao<T> {

    void batchInsert(List<T> list);
}
