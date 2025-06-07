package br.edu.ifba.inf008.uniManager.infra.repository;

import java.util.LinkedHashMap;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.repositoryInterface.participants.IParticipantRepository;

public class FileParticipantRepository implements IParticipantRepository{
    private final String filename = "participants.dat";
    
    @Override
    public void salvar(Participant participant) {
        // LinkedHashMap<String, Event> events = listar(); // carregar existentes
        // events.put(event.getId(), event);
        // try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
        //     oos.writeObject(events);
        // } catch (IOException e) {
        //     throw new RuntimeException("Erro ao salvar eventos", e);
        // }
    }

    @Override
    public LinkedHashMap<String, Participant> listar() {
        // File file = new File(filename);
        // if (!file.exists()) return new ArrayList<>();

        // try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        //     return (List<Event>) ois.readObject();
        // } catch (IOException | ClassNotFoundException e) {
        //     throw new RuntimeException("Erro ao ler events", e);
        // }

        return new LinkedHashMap<String, Participant>();
    }
}
