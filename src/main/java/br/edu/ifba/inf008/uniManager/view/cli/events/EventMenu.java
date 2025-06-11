package br.edu.ifba.inf008.uniManager.view.cli.events;

import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.Exceptions.BadRequestException;
import br.edu.ifba.inf008.uniManager.utils.MenuUtil;
import br.edu.ifba.inf008.uniManager.view.cli.IMenu;

public class EventMenu implements IMenu{
    private final EventManager eventManager;
    private final ParticipantManager participantManager;
    private final Scanner scanner;
    
    public EventMenu(EventManager eventManager, ParticipantManager participantManager){
        this.eventManager = eventManager;
        this.participantManager = participantManager;
        this.scanner = new Scanner(System.in);
    }

    public void show(){
        int choice;
        do {
            System.out.println(MenuUtil.clearTerminal());

        System.out.println("|===========================================|");
        System.out.println("|================= Events ==================|");
        System.out.println("|===========================================|");
        System.out.println("|1. Register a event                        |");
        System.out.println("|1. List all events                         |");
        System.out.println("|2. Search for a event                      |");
        System.out.println("|3. Delete a event                          |");
        System.out.println("|                                           |");
        System.out.println("|0. Exit                                    |");
        System.out.println("|===========================================|");
        
            choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 0: 
                        break;
                    case 1: 
                        new EventMenu(eventManager, participantManager).show();
                        break;
                    default:
                        throw new BadRequestException("Escolhe certo porra");
                }
            } catch (Exception e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        } while (choice != 0);

        return;
    }
}
