package br.edu.ifba.inf008.uniManager.view.cli.participants;

import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.exceptions.BadRequestException;
import br.edu.ifba.inf008.uniManager.utils.menu.MenuUtil;
import br.edu.ifba.inf008.uniManager.view.cli.IMenu;
import br.edu.ifba.inf008.uniManager.view.cli.events.crud.DetailEventMenu;
import br.edu.ifba.inf008.uniManager.view.cli.events.crud.ListEventMenu;
import br.edu.ifba.inf008.uniManager.view.cli.participants.crud.ListParticipantMenu;
import br.edu.ifba.inf008.uniManager.view.cli.participants.crud.RegisterParticipantMenu;

public class ParticipantMenu implements IMenu{
    private final EventManager eventManager;
    private final ParticipantManager participantManager;
    private final Scanner scanner;
    
    public ParticipantMenu(EventManager eventManager, ParticipantManager participantManager){
        this.eventManager = eventManager;
        this.participantManager = participantManager;
        this.scanner = new Scanner(System.in);
    }

    public void show(){
        int choice = -1;
        String line;
        do {
            System.out.println(MenuUtil.clearTerminal());

            System.out.println("===========================================");
            System.out.println("============== Participants ===============");
            System.out.println("===========================================");
            System.out.println("1. Register a Participant                  ");
            System.out.println("2. List Participants                       ");
            System.out.println("3. Detail an Participant                   ");
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
                        new RegisterParticipantMenu(eventManager, participantManager).show();
                        break;
                    case 2: 
                        new ListParticipantMenu(eventManager, participantManager).show();
                        break;
                    case 3: 
                        new DetailEventMenu(eventManager, participantManager).show();
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