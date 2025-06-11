package br.edu.ifba.inf008.uniManager.infra.repository;

import java.util.LinkedHashMap;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Student;
import br.edu.ifba.inf008.uniManager.domain.ports.repository.IRepository;

public class FileParticipantRepository implements IRepository<Participant>{
    private final String filename = "participants.dat";
    
    @Override
    public void save(Participant participant) {
        // LinkedHashMap<String, Participant> events = getAll(); // carregar existentes
        // events.put(event.getId(), event);
        // try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
        //     oos.writeObject(events);
        // } catch (IOException e) {
        //     throw new RuntimeException("Erro ao salvar eventos", e);
        // }
    }

    @Override
    public LinkedHashMap<String, Participant> getAll() {
        // File file = new File(filename);
        // if (!file.exists()) return new ArrayList<>();

        // try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        //     return (List<Event>) ois.readObject();
        // } catch (IOException | ClassNotFoundException e) {
        //     throw new RuntimeException("Erro ao ler events", e);
        // }

        return new LinkedHashMap<String, Participant>();
    }

    @Override
    public Participant findById(String cpf){
        Student e = new Student(cpf, cpf, cpf, cpf, cpf, null, cpf);//TEST
        return e; 
    }
}
