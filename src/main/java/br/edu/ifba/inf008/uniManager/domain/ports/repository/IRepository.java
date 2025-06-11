package br.edu.ifba.inf008.uniManager.domain.ports.repository;

import java.util.LinkedHashMap;

public interface IRepository<T> {
    void save(T element);
    boolean delete(String id);
    T findById(String cpf);
    LinkedHashMap<String, T> getAll();
}
