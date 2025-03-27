package com.example.demo.dao;

import java.util.Map;

import com.example.demo.entities.GenericEntity;

public interface IDao<T, E extends GenericEntity> {

    public T create(E e);

    public void update(E e);

    public void delete(T id);

    public E findById(T id);

    public Map<T, GenericEntity> findAll();

}
