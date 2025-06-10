package br.edu.ifba.inf008.uniManager.domain.repositoryInterface.participants;

import java.util.LinkedHashMap;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;

public interface IParticipantRepository {
    void save(Participant event);
    Participant findByCpf(String cpf);
    LinkedHashMap<String, Participant> getAll();
}
