package br.edu.ifba.inf008.uniManager.domain.repositoryInterface.events;

import java.util.LinkedHashMap;

import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;

public interface IEventRepository {
    void salvar(Event event);
    LinkedHashMap<String, Event> listar();
}
