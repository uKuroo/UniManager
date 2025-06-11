package br.edu.ifba.inf008.uniManager.view.cli.events;

import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;
import br.edu.ifba.inf008.uniManager.domain.ports.exports.ICertificateExporter;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.exceptions.BadRequestException;
import br.edu.ifba.inf008.uniManager.utils.menu.MenuUtil;
import br.edu.ifba.inf008.uniManager.view.cli.IMenu;
import br.edu.ifba.inf008.uniManager.view.cli.events.crud.DetailEventMenu;
import br.edu.ifba.inf008.uniManager.view.cli.events.crud.ListEventMenu;
import br.edu.ifba.inf008.uniManager.view.cli.events.crud.RegisterEventMenu;

public class EventMenu implements IMenu{
    private final EventManager eventManager;
    private final ParticipantManager participantManager;
    private final ICertificateExporter<Event> certificateExporter;
    private final Scanner scanner;
    
    public EventMenu(EventManager eventManager, ParticipantManager participantManager, ICertificateExporter<Event> certificateExporter){
        this.eventManager = eventManager;
        this.participantManager = participantManager;
        this.certificateExporter = certificateExporter;
        this.scanner = new Scanner(System.in);
    }

    public void show(){
        int choice = -1;
        String line;
        do {
            System.out.println(MenuUtil.clearTerminal());

            System.out.println("===========================================");
            System.out.println("================= Events ==================");
            System.out.println("===========================================");
            System.out.println("1. Register a event                        ");
            System.out.println("2. List events                             ");
            System.out.println("3. Detail an event                         ");
            System.out.println("                                           ");
            System.out.println("0. Back                                    ");
            System.out.println("===========================================");
        
            line = scanner.nextLine().trim();
            
            try {
                choice = Integer.parseInt(line);
                switch (choice) {
                    case 0: 
                        break;
                    case 1: 
                        new RegisterEventMenu(eventManager, participantManager).show();
                        break;
                    case 2: 
                        new ListEventMenu(eventManager, participantManager).show();
                        break;
                    case 3: 
                        new DetailEventMenu(eventManager, participantManager, certificateExporter).show();
                        break;
                    default:
                        throw new BadRequestException(choice + " isn't an option");
                }
            } catch (BadRequestException | NumberFormatException e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        } while (choice != 0);

        return;
    }
    
}
