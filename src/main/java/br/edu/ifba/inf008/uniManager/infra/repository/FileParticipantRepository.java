package br.edu.ifba.inf008.uniManager.infra.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.ports.repository.IRepository;

public class FileParticipantRepository implements IRepository<Participant>{
    private final String filename = "files/binaries/participants.dat";
    private LinkedHashMap<String, Participant> participants;


    public FileParticipantRepository(){
        this.participants = loadFromFile();
    }

    @Override
    public void save(Participant participant) {
        participants.put(participant.getCpf(), participant);
        saveToFile();
    }

    @Override
    public LinkedHashMap<String, Participant> getAll() {
        File file = new File(filename);
        if (!file.exists()) return new LinkedHashMap<String, Participant>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (LinkedHashMap<String, Participant>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao ler events", e);
        }
    }

    @Override
    public Participant findById(String cpf) {
        return participants.get(cpf);
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(participants);
        } catch (IOException e) {
            throw new RuntimeException("Error saving events to file .dat", e);
        }
    }

    @Override
    public boolean delete(String id) {
    if (participants.remove(id) != null) {
        saveToFile();
        return true;
    }
    return false;
    }   

    private LinkedHashMap<String, Participant> loadFromFile() {
        File file = new File(filename);

        if (!file.exists()) {
            return new LinkedHashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (LinkedHashMap<String, Participant>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error loading events to file .dat", e);
        }
    }
}
