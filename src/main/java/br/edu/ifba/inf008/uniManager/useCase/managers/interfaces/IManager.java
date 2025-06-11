package br.edu.ifba.inf008.uniManager.useCase.managers.interfaces;

import java.util.LinkedHashMap;

public interface IManager<T> {
    // CRUD
    void create(T obj);
    T get(String key);
    void update(String key, T obj);
    void delete(String key);
    LinkedHashMap<String, T> getAll();
    LinkedHashMap<String, T> getAllFromType(String type);
}
