package br.edu.ifba.inf008.uniManager;

import br.edu.ifba.inf008.uniManager.domain.ports.repository.IEventRepository;
import br.edu.ifba.inf008.uniManager.domain.ports.repository.IParticipantRepository;
import br.edu.ifba.inf008.uniManager.infra.repository.FileEventRepository;
import br.edu.ifba.inf008.uniManager.infra.repository.FileParticipantRepository;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.view.cli.HomeMenu;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        IEventRepository eventRepository = new FileEventRepository();
        EventManager eventManager = new EventManager(eventRepository);
        
        IParticipantRepository participantRepository = new FileParticipantRepository();
        ParticipantManager participantManager = new ParticipantManager(participantRepository);

        HomeMenu menuManager = new HomeMenu(eventManager, participantManager);

        // BasicITextTest iTextTest = new BasicITextTest();
        // iTextTest.export();
    }
}
