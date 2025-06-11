package br.edu.ifba.inf008.uniManager.useCase.managers.implementation;

import java.util.LinkedHashMap;
import java.util.Map;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.ports.repository.IRepository;
import br.edu.ifba.inf008.uniManager.useCase.managers.interfaces.IManager;

public class ParticipantManager implements IManager<Participant>{
    private static LinkedHashMap<String, Participant> participants;
    private final IRepository<Participant> participantRepository;

    public ParticipantManager(IRepository<Participant> participantRepository){
        this.participantRepository = participantRepository;
        this.participants = participantRepository.getAll();
    }

    @Override
    public void create(Participant participant){
        participants.put(participant.getCpf(), participant);
    }

    @Override
    public Participant get(String key){
        return participants.get(key);
    }

    @Override
    public void update(String key, Participant participant){
        participants.put(key, participant);
    }

    @Override
    public void delete(String key){
        participants.remove(key);
    }

    @Override
    public LinkedHashMap<String, Participant> getAll(){
        return participants;
    }

    @Override
    public LinkedHashMap<String, Participant> getAllFromType(String type){
        LinkedHashMap<String, Participant> fromType = new LinkedHashMap<>();

        for (Map.Entry<String, Participant> participant : participants.entrySet()) {
            Participant currentParticipant = participant.getValue();

            if(currentParticipant.getClass().getSimpleName().equals(type)){
                fromType.put(participant.getKey(), currentParticipant);
            }
        }

        return fromType;
    }
}
