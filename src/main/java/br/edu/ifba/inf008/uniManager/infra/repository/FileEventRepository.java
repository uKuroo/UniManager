package br.edu.ifba.inf008.uniManager.infra.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

import br.edu.ifba.inf008.uniManager.domain.entities.events.AcademicFair;
import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;
import br.edu.ifba.inf008.uniManager.domain.ports.repository.IRepository;

public class FileEventRepository implements IRepository<Event>{
    private final String filename = "files/binaries/events.dat";
    private LinkedHashMap<String, Event> events;


    public FileEventRepository(){
        this.events = loadFromFile();

        ensureDirectories();
    }

    private void ensureDirectories() {
        new File("files/binaries").mkdirs();
        new File("files/reports").mkdirs();
    }

    @Override
    public void save(Event event) {
        events.put(event.getId(), event);
        saveToFile();
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
        return events.get(eventId);
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(events);
        } catch (IOException e) {
            throw new RuntimeException("Error saving events to file .dat", e);
        }
    }

    @Override
    public boolean delete(String id) {
    if (events.remove(id) != null) {
        saveToFile();
        return true;
    }
    return false;
    }   

    private LinkedHashMap<String, Event> loadFromFile() {
        File file = new File(filename);

        if (!file.exists()) {
            return new LinkedHashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (LinkedHashMap<String, Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error loading events to file .dat", e);
        }
    }
}
