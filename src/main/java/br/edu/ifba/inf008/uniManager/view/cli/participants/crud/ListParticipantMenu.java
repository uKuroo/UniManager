package br.edu.ifba.inf008.uniManager.view.cli.participants.crud;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.domain.entities.events.*;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.*;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.exceptions.BadRequestException;
import br.edu.ifba.inf008.uniManager.utils.menu.MenuUtil;
import br.edu.ifba.inf008.uniManager.view.cli.IMenu;

public class ListParticipantMenu implements IMenu{
    private final EventManager eventManager;
    private final ParticipantManager participantManager;
    private final Scanner scanner;
    
    public ListParticipantMenu(EventManager eventManager, ParticipantManager participantManager){
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
            System.out.println("Select a type of participant to list");
            System.out.println("===========================================");
            System.out.println("1. All                                     ");
            System.out.println("2. Student                                 ");
            System.out.println("3. Teacher                                 ");
            System.out.println("4. External                                ");
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
                        this.listParticipants();
                        break;
                    case 2: 
                        this.listParticipants("Student");
                        break;
                    case 3: 
                        this.listParticipants("Teacher");
                        break;
                    case 4: 
                        this.listParticipants("External");
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

    public void listParticipants(){
        LinkedHashMap<String, Participant> participants = participantManager.getAll();

        showListParticipants(participants, "All");
    }
    
    public void listParticipants(String type){
        LinkedHashMap<String, Participant> participants = participantManager.getAllFromType(type);

        showListParticipants(participants, type);
    }

    private void showListParticipants(LinkedHashMap<String, Participant> participants, String type){
        System.out.println(MenuUtil.clearTerminal());

        System.out.println("===========================================");
        System.out.println("List: "+type);
        System.out.println("===========================================");
        if(participants.size() == 0)
            System.out.println("Looks empty...                    ");
        else
            for (Map.Entry<String, Participant> participant : participants.entrySet()) {
                System.out.println("Cpf: "+participant.getValue().getCpf());
                System.out.println("Name: "+participant.getValue().getName());
                System.out.println("Email: "+participant.getValue().getEmail());
                System.out.println("Address: "+participant.getValue().getAddress());
                System.out.println("Phone: "+participant.getValue().getphone());
                System.out.println("Birthdate: "+participant.getValue().getBirthDate());
                if(participant.getValue().getType().equals("Student")){
                    Student student = (Student)participant.getValue();

                    System.out.println("Instituition: "+student.getInstituition());
                    System.out.println("Registration: "+student.getRegistration());
                }
                if(participant.getValue().getType().equals("Teacher")){
                    Teacher teacher = (Teacher)participant.getValue();

                    System.out.println("Department: "+teacher.getDepartment());
                    System.out.println("Internal Registration: "+teacher.getInternalRegistration());
                }
                if(participant.getValue().getType().equals("External")){
                    External external = (External)participant.getValue();

                    System.out.println("Organization: "+external.getOrganization());
                    System.out.println("Role: "+external.getRole());
                }
                System.out.println("-------------------------------------------");
            }
        System.out.println("                                           ");
        System.out.println("Press anything to close                    ");
        System.out.println("===========================================");

        MenuUtil.waitAnyInput();

        return;
    }
}
