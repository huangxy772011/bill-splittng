package com.ascending.project.repository;

import java.util.List;

public interface CRUDDao<T, ID> {
    T save(T t);
    List<T> findAll();
    T findById(ID id);
}
