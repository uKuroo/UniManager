package br.edu.ifba.inf008.uniManager.useCase.managers.implementation;

import java.util.LinkedHashMap;
import java.util.Map;

import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;
import br.edu.ifba.inf008.uniManager.domain.ports.repository.IRepository;
import br.edu.ifba.inf008.uniManager.useCase.managers.interfaces.IManager;

public class EventManager implements IManager<Event>{
    private static LinkedHashMap<String, Event> events;
    private final IRepository<Event> repository;

    public EventManager(IRepository<Event> repository){
        this.repository = repository;
        this.events = repository.getAll();
    }

    @Override
    public void create(Event event){
        events.put(event.getId(), event);
        repository.save(event);
    }

    @Override
    public Event get(String id){
        return events.get(id);
    }

    @Override
    public void update(String id, Event event){
        events.put(id, event);
        repository.save(event);
    }

    @Override
    public void delete(String id){
        events.remove(id);
        repository.delete(id);
    }

    @Override
    public LinkedHashMap<String, Event> getAll(){
        return events;
    }
    
    @Override
    public LinkedHashMap<String, Event> getAllFromType(String type){
        LinkedHashMap<String, Event> fromType = new LinkedHashMap<>();

        for (Map.Entry<String, Event> event : events.entrySet()) {
            Event currentEvent = event.getValue();

            if(currentEvent.getClass().getSimpleName().equals(type)){
                fromType.put(event.getKey(), currentEvent);
            }
        }

        return fromType;
    }

    public boolean export(String id){
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
