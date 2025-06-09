package br.edu.ifba.inf008.uniManager;

import br.edu.ifba.inf008.uniManager.domain.repositoryInterface.events.IEventRepository;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
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

        MenuManager menuManager = new MenuManager(eventManager, participantManager);
    }
}
