package br.edu.ifba.inf008.uniManager;

import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.ports.repository.IRepository;
import br.edu.ifba.inf008.uniManager.infra.Exports.BasicITextTest;
import br.edu.ifba.inf008.uniManager.infra.repository.FileEventRepository;
import br.edu.ifba.inf008.uniManager.infra.repository.FileParticipantRepository;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        IRepository<Event> eventRepository = new FileEventRepository();
        EventManager eventManager = new EventManager(eventRepository);
        
        IRepository<Participant> participantRepository = new FileParticipantRepository();
        ParticipantManager participantManager = new ParticipantManager(participantRepository);

        //HomeMenu menuManager = new HomeMenu(eventManager, participantManager);

        BasicITextTest text = new BasicITextTest();
        text.export();
    }
}
