package br.edu.ifba.inf008.uniManager.infra.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import br.edu.ifba.inf008.uniManager.domain.entities.events.AcademicFair;
import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;
import br.edu.ifba.inf008.uniManager.domain.ports.repository.IRepository;

public class FileEventRepository implements IRepository<Event>{
    private final String filename = "events.dat";
    
    @Override
    public void save(Event event) {
        LinkedHashMap<String, Event> events = getAll(); // carregar existentes
        events.put(event.getId(), event);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(events);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar eventos", e);
        }
    }

    @Override
    public LinkedHashMap<String, Event> getAll() {
        File file = new File(filename);
        if (!file.exists()) return new LinkedHashMap<String, Event>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (LinkedHashMap<String, Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao ler events", e);
        }
    }

    @Override
    public Event findById(String eventId){
        AcademicFair e = new AcademicFair(eventId, eventId, eventId, null, eventId, 0, 0, null);
        return e;
    }
}
