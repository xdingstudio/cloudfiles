package com.bigroad.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDAOI<T> {
	  //公共的保存方法

    public Serializable save(T o);



    //单个删除

    public void delete(T o);



    //修改

    public void update(T o);



    //修改或者保存

    public void saveOrUpdate(T o);



    //通过hql语句查询 单个

    public T get(String hql);



    //通过hql语句有条件的查询 单个

    public T get(String hql, Map<String, Object> params);



    //通过hql语句查询列表 多条

    public List<T> find(String hql);



    //通过hql和条件语句查询列表 多条

    public List<T> find(String hql, Map<String, Object> params);



    //分页查询

    public List<T> find(String hql, int page, int rows);



    //获得总页数

    public Long count(String hql);



    //有条件的分页查询

    public List<T> find(String hql, Map<String, Object> params, int page, int rows);



    //获得总页数

    public Long count(String hql, Map<String, Object> params);
    
    public void deleteById(String hql, Map<String, Object> params);
	
	
}
