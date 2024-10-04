package com.example.cinema1.repositories;

public interface CUDRepository {
    void setCreate(boolean canCreate);
    void setUpdate(boolean canUpdate);
    void setDelete(boolean canDelete);
    <T> void createEntity(T entity);
    <T> void updateEntity(T entity);
    <T> void deleteEntity(Class<T> entityClass, Object id);
}

