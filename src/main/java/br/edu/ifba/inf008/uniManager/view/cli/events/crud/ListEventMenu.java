package br.edu.ifba.inf008.uniManager.view.cli.events.crud;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.exceptions.BadRequestException;
import br.edu.ifba.inf008.uniManager.utils.menu.MenuUtil;

public class ListEventMenu{
    private final EventManager eventManager;
    private final ParticipantManager participantManager;
    private final Scanner scanner;
    
    public ListEventMenu(EventManager eventManager, ParticipantManager participantManager){
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
            System.out.println("Select a type of Event to list");
            System.out.println("===========================================");
            System.out.println("1. All                                     ");
            System.out.println("2. Academic Fair                           ");
            System.out.println("3. Workshop                                ");
            System.out.println("4. Course                                  ");
            System.out.println("5. Lecture                                 ");
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
                        this.listEvents();
                        break;
                    case 2: 
                        this.listEvents("AcademicFair");
                        break;
                    case 3: 
                        this.listEvents("Workshop");
                        break;
                    case 4: 
                        this.listEvents("ShortCourse");
                        break;
                    case 5: 
                        this.listEvents("Lecture");
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

    public void listEvents(){
        LinkedHashMap<String, Event> events = eventManager.getAll();

        showListEvents(events, "All");
    }
    
    public void listEvents(String type){
        LinkedHashMap<String, Event> events = eventManager.getAllFromType(type);

        showListEvents(events, type);
    }

    private void showListEvents(LinkedHashMap<String, Event> events, String type){
        System.out.println(MenuUtil.clearTerminal());

        System.out.println("===========================================");
        System.out.println("List: "+type);
        System.out.println("===========================================");
        if(events.size() == 0)
            System.out.println("Looks empty...                    ");
        else
            for (Map.Entry<String, Event> event : events.entrySet()) {
                System.out.println("Id: "+event.getValue().getId());
                System.out.println("Type: "+event.getValue().getType());
                System.out.println("Title: "+event.getValue().getTitle());
                System.out.println("Description: "+event.getValue().getDescription());
                System.out.println("Date: "+event.getValue().getDate());
                System.out.println("Capacity: "+event.getValue().getVacancy());
                System.out.println("-------------------------------------------");
            }
        System.out.println("                                           ");
        System.out.println("Press anything to close                    ");
        System.out.println("===========================================");

        MenuUtil.waitAnyInput();

        return;
    }
}
