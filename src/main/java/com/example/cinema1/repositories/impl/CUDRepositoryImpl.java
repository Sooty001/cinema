package com.example.cinema1.repositories.impl;

import com.example.cinema1.repositories.CUDRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CUDRepositoryImpl implements CUDRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private boolean canCreate = false;
    private boolean canUpdate = false;
    private boolean canDelete = false;

    public void setCreate(boolean canCreate) {
        this.canCreate = canCreate;
    }

    public void setUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public void setDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }


    @Transactional
    public <T> void createEntity(T entity) {
        if (canCreate) {
            entityManager.persist(entity);
        } else {
            throw new UnsupportedOperationException("Creation is prohibited for this service");
        }
    }

    @Transactional
    public <T> void updateEntity(T entity) {
        if (canUpdate) {
            entityManager.merge(entity);
        } else {
            throw new UnsupportedOperationException("Modification is prohibited for this service");
        }
    }

    @Transactional
    public <T> void deleteEntity(Class<T> entityClass, Object id) {
        if (canDelete) {
            T entity = entityManager.find(entityClass, id);
            if (entity != null) {
                entityManager.remove(entity);
            }
        } else {
            throw new UnsupportedOperationException("Deletion is prohibited for this service");
        }
    }
}
