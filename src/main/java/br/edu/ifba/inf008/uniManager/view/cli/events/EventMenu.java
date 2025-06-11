package br.edu.ifba.inf008.uniManager.view.cli.events;

import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.Exceptions.BadRequestException;
import br.edu.ifba.inf008.uniManager.utils.MenuUtil;
import br.edu.ifba.inf008.uniManager.view.cli.IMenu;
import br.edu.ifba.inf008.uniManager.view.cli.events.crud.ListEventMenu;
import br.edu.ifba.inf008.uniManager.view.cli.events.crud.RegisterEventMenu;

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
        int choice = -1;
        String line;
        do {
            System.out.println(MenuUtil.clearTerminal());

            System.out.println("===========================================");
            System.out.println("================= Events ==================");
            System.out.println("===========================================");
            System.out.println("1. Register a event                        ");
            System.out.println("2. List events                             ");
            System.out.println("3. Search for a event                      ");
            System.out.println("4. Delete a event                          ");
            System.out.println("                                           ");
            System.out.println("0. Exit                                    ");
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
                        chooseList();
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

    private void chooseList(){
        int choice = -1;
        String line;
        do {
            System.out.println(MenuUtil.clearTerminal());

            System.out.println("===========================================");
            System.out.println("Select a type of Event to list");
            System.out.println("===========================================");
            System.out.println("1. All                                     ");
            System.out.println("2. Academic Fair                           ");
            System.out.println("3. Workshop                                ");
            System.out.println("4. Course                                  ");
            System.out.println("5. Lecture                                 ");
            System.out.println("                                           ");
            System.out.println("0. Exit                                    ");
            System.out.println("===========================================");
        
            line = scanner.nextLine().trim();
            
            try {
                choice = Integer.parseInt(line);
                switch (choice) {
                    case 0: 
                        break;
                    case 1: 
                        new ListEventMenu(eventManager, participantManager).listEvents();
                        break;
                    case 2: 
                        new ListEventMenu(eventManager, participantManager).listEvents("AcademicFair");
                        break;
                    case 3: 
                        new ListEventMenu(eventManager, participantManager).listEvents("Workshop");
                        break;
                    case 4: 
                        new ListEventMenu(eventManager, participantManager).listEvents("ShortCourse");
                        break;
                    case 5: 
                        new ListEventMenu(eventManager, participantManager).listEvents("Lecture");
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
