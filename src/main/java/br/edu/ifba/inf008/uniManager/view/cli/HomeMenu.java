package br.edu.ifba.inf008.uniManager.view.cli;

import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.MenuUtil;

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
        System.out.println("|===========================================|");
        System.out.println("|=============== UniManager ================|");
        System.out.println("|===========================================|");
        System.out.println("|1. Manage Events                           |");
        System.out.println("|2. Manage Participants                     |");
        System.out.println("|3. Manage Certificates                     |");
        System.out.println("|                                           |");
        System.out.println("|0. Exit                                    |");
        System.out.println("|===========================================|");
        
        int choice;
        do { 
            choice = scanner.nextInt();
            switch (choice) {
                case 1: 
                    System.out.println(MenuUtil.clearTerminal());
                    break;
                default:
                    continue;
            }
        } while (choice != 0);
    }
}
