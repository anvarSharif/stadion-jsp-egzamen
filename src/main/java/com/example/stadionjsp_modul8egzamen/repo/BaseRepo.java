package com.example.stadionjsp_modul8egzamen.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import static com.example.stadionjsp_modul8egzamen.config.MyListener.emf;

public class BaseRepo<T> {
    private final Class<T> persistenceClass;


    public BaseRepo() {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.persistenceClass = clazz;
    }

    @Transactional
    public void save(T entity) {
        try (
                EntityManager entityManager = emf.createEntityManager()
        ) {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }
    }

    public List<T> findAll() {
        try (
                EntityManager entityManager = emf.createEntityManager()
        ) {
           return entityManager.createQuery("from %s".formatted(persistenceClass.getSimpleName()),persistenceClass).getResultList();
        }
    }
    public void update(T entity){
        try (
                EntityManager entityManager = emf.createEntityManager()
        ) {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }
    }
    public Optional<T> findById(Integer id) {
        try (
                EntityManager entityManager = emf.createEntityManager()
        ) {
            entityManager.getTransaction().begin();
            T t = entityManager.find(persistenceClass, id);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        }
    }
    public void deleteById(Integer id) {
        try (
                EntityManager entityManager = emf.createEntityManager()
        ) {
            entityManager.getTransaction().begin();
            T t = entityManager.find(persistenceClass, id);
            if (t != null) {
                entityManager.remove(t);
            }
            entityManager.getTransaction().commit();
        }
    }

}








