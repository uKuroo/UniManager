package br.edu.ifba.inf008.uniManager.domain.ports.repository;

import java.util.LinkedHashMap;

import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;

public interface IEventRepository {
    void save(Event event);
    Event findById(String id);
    LinkedHashMap<String, Event> getAll();
}
