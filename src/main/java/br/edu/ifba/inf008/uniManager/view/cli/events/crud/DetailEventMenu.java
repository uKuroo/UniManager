package br.edu.ifba.inf008.uniManager.view.cli.events.crud;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.domain.entities.events.AcademicFair;
import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;
import br.edu.ifba.inf008.uniManager.domain.entities.events.Lecture;
import br.edu.ifba.inf008.uniManager.domain.entities.events.ShortCourse;
import br.edu.ifba.inf008.uniManager.domain.entities.events.Workshop;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.ports.exports.ICertificateExporter;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.exceptions.BadRequestException;
import br.edu.ifba.inf008.uniManager.utils.menu.MenuUtil;
import br.edu.ifba.inf008.uniManager.view.cli.IMenu;

public class DetailEventMenu implements IMenu{
    private final EventManager eventManager;
    private final ParticipantManager participantManager;
    private final ICertificateExporter<Event> certificateExporter;
    private final Scanner scanner;
    
    public DetailEventMenu(EventManager eventManager, ParticipantManager participantManager, ICertificateExporter<Event> certificateExporter){
        this.eventManager = eventManager;
        this.participantManager = participantManager;
        this.certificateExporter = certificateExporter;
        this.scanner = new Scanner(System.in);
    }

    public void show(){
        String line = "";
        do {
            System.out.println(MenuUtil.clearTerminal());

            System.out.println("===========================================");
            System.out.println("Insert the id from the event wanted");
            System.out.println("(input 0 to go back)");
            System.out.println("===========================================");
        
            line = scanner.nextLine().trim();
            if(line.equals("0")) return;
            
            try {
                showDetailedEvent(line);
            } catch (BadRequestException e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        } while(true);
    }

    private boolean showDetailedEvent(String id){
        int choice = -1;
        String line;

        Event event = eventManager.get(id);
        if(event == null) throw new BadRequestException("This event does not exist!");
        do { 
            
            LinkedHashMap<String, Participant> participants = event.getParticipants();
            
            System.out.println(MenuUtil.clearTerminal());
            
            System.out.println("===========================================");
            System.out.println("Details of "+event.getType()+" ("+event.getId()+")");
            System.out.println("===========================================");
            System.out.println("Id: "+event.getId());
            System.out.println("Type: "+event.getType());
            System.out.println("Title: "+event.getTitle());
            System.out.println("Description: "+event.getDescription());
            System.out.println("Date: "+event.getDate());
            System.out.println("Capacity: "+event.getVacancy());
            showSpecificAttribute(event);
            System.out.println("===========================================");
            System.out.println("Participants:                              ");
            System.out.println("===========================================");
            for (Map.Entry<String, Participant> participant : participants.entrySet()) {
                System.out.println("Cpf: "+participant.getValue().getCpf());
                System.out.println("Name: "+participant.getValue().getName());
                System.out.println("Type: "+participant.getValue().getType());
                System.out.println("Email: "+participant.getValue().getEmail());
                System.out.println("Phone: "+participant.getValue().getphone());
                System.out.println("Birthdate: "+participant.getValue().getBirthDate());
                System.out.println("-------------------------------------------");
            }
            System.out.println("                                           ");
            System.out.println("1. Subscribe a Participant                 ");
            System.out.println("2. unsubscribe a Participant               ");
            System.out.println("3. Export a current report                 ");
            System.out.println("4. Delete                                  ");
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
                        subscribeParticipant(id);
                        break;
                    case 2: 
                        subscribeParticipant(id);
                        break;
                    case 3: 
                        try {
                            certificateExporter.export(eventManager.get(id));

                            MenuUtil.successScreen("Pdf was created at files folder!");
                        } catch (Exception e) {
                            MenuUtil.errorScreen(e.getMessage());
                        }

                        break;
                    case 4: 
                        if(new DeleteEventMenu(eventManager, participantManager).showConfirmation(id)) return true;
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

    private void unSubscribeParticipant(String id){
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

                if(!event.unSubscribeParticipant(input)) throw new BadRequestException("Incorrect cpf or the Participant is not included in the Event");
                
                MenuUtil.successScreen(participant.getName()+" leave the "+ event.getType());
            } catch (BadRequestException e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        } while (!input.equals("back"));
    }

    private void showSpecificAttribute(Event event){
        if(event instanceof AcademicFair){
            AcademicFair academicFair = (AcademicFair) event;
            System.out.println("Number of Stands: "+ (academicFair.getNumberOfStands()));
            System.out.println("Organizer "+ (academicFair.getOrganizer().getName())+" Cpf: "+ (academicFair.getOrganizer().getCpf()));
        }
        if(event instanceof Lecture){
            Lecture lecture = (Lecture) event;
            System.out.println("Theme: "+ (lecture.getTheme()));
            System.out.println("Speaker "+ (lecture.getSpeaker().getName())+" Cpf: "+ (lecture.getSpeaker().getCpf()));
        }
        if(event instanceof ShortCourse){
            ShortCourse shortCourse = (ShortCourse) event;
            System.out.println("Subject: "+ (shortCourse.getSubject()));
            System.out.println("Teacher: "+ (shortCourse.getTeacher().getName())+" Cpf: "+ (shortCourse.getTeacher().getCpf()));
        }
        if(event instanceof Workshop){
            Workshop workshop = (Workshop) event;
            System.out.println("Needs Material: "+ (workshop.needMaterial()));
            System.out.println("Instructor "+ (workshop.getInstructor().getName())+" Cpf: "+ (workshop.getInstructor().getCpf()));
        }
    }
}
