package br.edu.ifba.inf008.uniManager.view.cli;

import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.Exceptions.BadRequestException;
import br.edu.ifba.inf008.uniManager.utils.MenuUtil;
import br.edu.ifba.inf008.uniManager.view.cli.events.EventMenu;
import br.edu.ifba.inf008.uniManager.view.cli.participants.ParticipantMenu;

public class HomeMenu implements IMenu {
    private final EventManager eventManager;
    private final ParticipantManager participantManager;
    private final Scanner scanner;

    public HomeMenu(EventManager eventManager, ParticipantManager participantManager){
        this.eventManager = eventManager;
        this.participantManager = participantManager;
        this.scanner = new Scanner(System.in);

        show();
    }


    public void show(){
        int choice;
        String line;
        do {
            System.out.println(MenuUtil.clearTerminal());

            System.out.println("===========================================");
            System.out.println("=============== UniManager ================");
            System.out.println("===========================================");
            System.out.println("1. Manage Events                           ");
            System.out.println("2. Manage Participants                     ");
            System.out.println("3. Manage Certificates                     ");
            System.out.println("                                           ");
            System.out.println("0. Exit                                    ");
            System.out.println("===========================================");
        
            line = scanner.nextLine().trim();
            try {
                choice = Integer.parseInt(line);
                switch (choice) {
                    case 0: 
                        if(MenuUtil.exitConfirm()) return;
                        break;
                    case 1: 
                        new EventMenu(eventManager, participantManager).show();
                        break;
                    case 2:
                        new ParticipantMenu(eventManager, participantManager).show();
                        break;
                    default:
                        throw new BadRequestException(choice + " isn't an option");
                }
            } catch (BadRequestException | NumberFormatException e) {
                MenuUtil.errorScreen(e.getClass().getSimpleName() + " " + e.getMessage() + " ");
            }
        } while (true);
    }
}
