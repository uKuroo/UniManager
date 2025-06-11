package br.edu.ifba.inf008.uniManager;

import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.ports.exports.ICertificateExporter;
import br.edu.ifba.inf008.uniManager.domain.ports.repository.IRepository;
import br.edu.ifba.inf008.uniManager.infra.exports.ITextEventExporter;
import br.edu.ifba.inf008.uniManager.infra.repository.FileEventRepository;
import br.edu.ifba.inf008.uniManager.infra.repository.FileParticipantRepository;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.view.cli.HomeMenu;

public class App {
    public static void main(String[] args) {
        ICertificateExporter<Event> certificateExporter = new ITextEventExporter();
        IRepository<Event> eventRepository = new FileEventRepository();
        EventManager eventManager = new EventManager(eventRepository, certificateExporter);
        
        IRepository<Participant> participantRepository = new FileParticipantRepository();
        ParticipantManager participantManager = new ParticipantManager(participantRepository);

        HomeMenu menuManager = new HomeMenu(eventManager, participantManager, certificateExporter);
    }
}
