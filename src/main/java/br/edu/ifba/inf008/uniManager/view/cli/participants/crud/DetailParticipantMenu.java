package br.edu.ifba.inf008.uniManager.view.cli.participants.crud;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.External;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Student;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Teacher;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.exceptions.BadRequestException;
import br.edu.ifba.inf008.uniManager.utils.menu.MenuUtil;
import br.edu.ifba.inf008.uniManager.view.cli.IMenu;

public class DetailParticipantMenu implements IMenu{
    private final EventManager eventManager;
    private final ParticipantManager participantManager;
    private final Scanner scanner;
    
    public DetailParticipantMenu(EventManager eventManager, ParticipantManager participantManager){
        this.eventManager = eventManager;
        this.participantManager = participantManager;
        this.scanner = new Scanner(System.in);
    }

    public void show(){
        String line = "";
        do {
            System.out.println(MenuUtil.clearTerminal());

            System.out.println("===========================================");
            System.out.println("Insert the id from the participant wanted");
            System.out.println("(input 0 to go back)");
            System.out.println("===========================================");
        
            line = scanner.nextLine().trim();
            if(line.equals("0")) return;
            
            try {
                showDetailedParticipant(line);
            } catch (BadRequestException e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        } while(true);
    }

    private boolean showDetailedParticipant(String cpf){
        int choice = -1;
        String line;

        Participant participant = participantManager.get(cpf);
        if(participant == null) throw new BadRequestException("This participant does not exist!");
        do { 
            System.out.println(MenuUtil.clearTerminal());

            LinkedHashMap<String, Event> eventsIncluded = new LinkedHashMap<>();
            for (String eventId : participant.getEventsIdIncluded()){
                Event event = eventManager.get(eventId);
                eventsIncluded.put(event.getId(), event);
            }
            
            System.out.println("===========================================");
            System.out.println("Details of "+participant.getType()+" ("+participant.getCpf()+")");
            System.out.println("===========================================");
            System.out.println("Cpf: "+participant.getCpf());
            System.out.println("Name: "+participant.getName());
            System.out.println("Type: "+participant.getType());
            System.out.println("Email: "+participant.getEmail());
            System.out.println("Address: "+participant.getAddress());
            System.out.println("Phone: "+participant.getphone());
            System.out.println("Birthdate: "+participant.getBirthDate());
            showSpecificAttribute(participant);
            System.out.println("===========================================");
            System.out.println("Events Included:                              ");
            System.out.println("===========================================");
            for (Map.Entry<String, Event> event : eventsIncluded.entrySet()) {
                System.out.println("Id: "+event.getValue().getId());
                System.out.println("Title: "+event.getValue().getTitle());
                System.out.println("Type: "+event.getValue().getType());
                System.out.println("Local: "+event.getValue().getLocal());
                System.out.println("Date: "+event.getValue().getDate());
                System.out.println("Capacity: "+event.getValue().getVacancy());
                System.out.println("-------------------------------------------");
            }
            System.out.println("                                           ");
            System.out.println("1. Delete                                  ");
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
                        if(new DeleteParticipantMenu(eventManager, participantManager).showConfirmation(cpf)) return true;
                    default:
                        throw new BadRequestException(choice + " isn't an option");
                }
            } catch (BadRequestException | NumberFormatException e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        } while (choice != 0);

        return true;
    }
        
    private void subscribeParticipant(String id){
        String input = "";
        do { 
            
            System.out.println(MenuUtil.clearTerminal());
            
            System.out.println("===========================================");
            System.out.println("Insert the CPF of the desired participant  ");
            System.out.println("(type 0 to go back)                        ");
            System.out.println("===========================================");
            
            try {
                input = scanner.nextLine();
                if (input.equals("0")) return;
                
                Participant participant = participantManager.get(input);
                Event event = eventManager.get(id);

                if(participant == null) throw new BadRequestException("Incorrect cpf!");

                event.subscribeParticipant(participant);

                eventManager.update(id, event);
                
                MenuUtil.successScreen(participant.getName()+" is now participating to the "+ event.getType());
            } catch (BadRequestException e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        } while (!input.equals("back"));
    }

    private void showSpecificAttribute(Participant participant){
        if(participant instanceof Student){
            Student student = (Student) participant;
            System.out.println("Instituition: "+ (student.getInstituition()));
            System.out.println("Registration "+ (student.getRegistration()));
        }
        if(participant instanceof Teacher){
            Teacher teacher = (Teacher) participant;
            System.out.println("Department: "+ (teacher.getDepartment()));
            System.out.println("Internal Registration "+ (teacher.getInternalRegistration()));
        }
        if(participant instanceof External){
            External external = (External) participant;
            System.out.println("Organization: "+ (external.getOrganization()));
            System.out.println("Role: "+ (external.getRole()));
        }
    }
}
