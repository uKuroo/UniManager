package br.edu.ifba.inf008.uniManager.view.cli.events.crud;

import java.time.LocalDate;
import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Teacher;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.exceptions.BadRequestException;
import br.edu.ifba.inf008.uniManager.utils.menu.CrudMenuUtil;
import br.edu.ifba.inf008.uniManager.utils.menu.MenuUtil;
import br.edu.ifba.inf008.uniManager.view.cli.IMenu;

public class RegisterEventMenu implements IMenu{
    private final EventManager eventManager;
    private final ParticipantManager participantManager;
    private final Scanner scanner;
    private final String classPath = "br.edu.ifba.inf008.uniManager.domain.entities.events.";
    
    public RegisterEventMenu(EventManager eventManager, ParticipantManager participantManager){
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
            System.out.println("Select a type of Event to register");
            System.out.println("===========================================");
            System.out.println("1. Academic Fair                           ");
            System.out.println("2. Workshop                                ");
            System.out.println("3. Course                                  ");
            System.out.println("4. Lecture                                 ");
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
                        showStepScreen("AcademicFair");
                        break;
                    case 2: 
                        showStepScreen("Workshop");
                        break;
                    case 3: 
                        showStepScreen("ShortCourse");
                        break;
                    case 4: 
                        showStepScreen("Lecture");
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

    private void showStepScreen(String type){
        String title = CrudMenuUtil.readStringBasic("Title", type);
        String id = CrudMenuUtil.readId(eventManager, type);
        String description = CrudMenuUtil.readStringBasic("Description", type);
        LocalDate date = CrudMenuUtil.readDate(type);
        String local = CrudMenuUtil.readStringBasic("Local", type);
        int capacity = CrudMenuUtil.readIntBasic("capacity", type);

        readSpecificsAttributesAndCreate(title, id, description, date, local, capacity, type);
    }

    private void readSpecificsAttributesAndCreate(String title, String id, String description, LocalDate date, String local, int capacity, String type){
        if(type.equals("AcademicFair")){
            try {
                int numberOfStands = CrudMenuUtil.readIntBasic("number of stands", type);
                
                Participant organizer = null;

                while(organizer == null){
                    String organizerCpf = CrudMenuUtil.readStringBasic("organizer Cpf", type);
                    organizer = participantManager.get(organizerCpf);
                }
    
                Class<?> clazz = Class.forName(classPath+type);
                Event event = (Event)clazz.getDeclaredConstructor(
                    String.class , String.class, String.class, LocalDate.class, String.class, int.class, int.class, Participant.class
                ).newInstance(
                    title, id, description, date, local, capacity, numberOfStands, organizer
                ); 
    
                eventManager.create(event);
            } catch (Exception e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        }
        if(type.equals("Workshop")){
            try {
                int numberOfMaterials = CrudMenuUtil.readIntBasic("number of Materials", type);
                
                Participant instructor = null;

                while(instructor == null){
                    String instructorCpf = CrudMenuUtil.readStringBasic("instructor Cpf", type);
                    instructor = participantManager.get(instructorCpf);
                }
                Class<?> clazz = Class.forName(classPath+type);
                Event event = (Event)clazz.getDeclaredConstructor(
                    String.class , String.class, String.class, LocalDate.class, String.class, int.class, Participant.class, boolean.class
                ).newInstance(
                    title, id, description, date, local, capacity, instructor, numberOfMaterials > 0
                ); 
    
                eventManager.create(event);
            } catch (Exception e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        }
        if(type.equals("ShortCourse")){
            try {
                String subject = CrudMenuUtil.readStringBasic("subject", type);

                Participant teacher = null;

                while(true){
                    String teacherCpf = CrudMenuUtil.readStringBasic("teacher Cpf", type);
                    teacher = participantManager.get(teacherCpf);

                    if(teacher instanceof Teacher) break;
                    MenuUtil.errorScreen("Only Teachers are allowed");
                }
    
                Class<?> clazz = Class.forName(classPath+type);
                Event event = (Event)clazz.getDeclaredConstructor(
                    String.class , String.class, String.class, LocalDate.class, String.class, int.class, Teacher.class, String.class
                ).newInstance(
                    title, id, description, date, local, capacity, teacher, subject
                ); 
    
                eventManager.create(event);
            } catch (Exception e) {
                MenuUtil.errorScreen(e.getMessage());
                 e.printStackTrace();
            }
        }
        if(type.equals("Lecture")){
            try {
                String theme = CrudMenuUtil.readStringBasic("theme", type);

                Participant speaker = null;

                while(speaker == null){
                    String speakerCpf = CrudMenuUtil.readStringBasic("speaker Cpf", type);
                    speaker = participantManager.get(speakerCpf);
                }
    
                Class<?> clazz = Class.forName(classPath+type);
                Event event = (Event)clazz.getDeclaredConstructor(
                    String.class , String.class, String.class, LocalDate.class, String.class, int.class, Participant.class, String.class
                ).newInstance(
                    title, id, description, date, local, capacity, speaker, theme
                ); 
    
                eventManager.create(event);
            } catch (Exception e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        }
    }

}
