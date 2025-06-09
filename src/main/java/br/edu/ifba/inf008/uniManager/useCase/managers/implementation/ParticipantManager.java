package br.edu.ifba.inf008.uniManager.useCase.managers.implementation;

import java.util.LinkedHashMap;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.repositoryInterface.participants.IParticipantRepository;
import br.edu.ifba.inf008.uniManager.useCase.managers.interfaces.IManager;

public class ParticipantManager implements IManager<Participant>{
    private static LinkedHashMap<String, Participant> participants;
    private final IParticipantRepository participantRepository;

    public ParticipantManager(IParticipantRepository participantRepository){
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
}
