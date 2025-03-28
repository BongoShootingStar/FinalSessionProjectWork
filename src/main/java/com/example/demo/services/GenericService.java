package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.example.demo.dao.IDao;
import com.example.demo.entities.GenericEntity;

import lombok.Getter;

@Getter
public abstract class GenericService<T, E extends GenericEntity, D extends IDao<T, E>> {

    @Autowired
    private D dao;

    @Autowired
    private ApplicationContext context;

    public abstract E construct(Map<String, String> entita);

    public List<E> findAll() {
        Map<T, GenericEntity> result = dao.findAll();
        List<E> list = new ArrayList<>();
        for (GenericEntity e : result.values()) {
            list.add((E) e);
        }
        return new ArrayList<>(dao.findAll().values().stream().map(e -> (E) e).toList());
    }

    public E findById(T id) {
        return dao.findById(id);
    }

    public void update(Map<String, String> params) {
        E entity = construct(params);
        dao.update(entity);
    }

    public void delete(T id) {
        dao.delete(id);
    }
}
