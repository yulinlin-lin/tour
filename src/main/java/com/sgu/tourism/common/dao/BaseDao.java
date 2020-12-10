package com.sgu.tourism.common.dao;

public interface BaseDao<T> {
    public Integer insertObject(T entity);
    public Integer updateObject(T entity);
}
