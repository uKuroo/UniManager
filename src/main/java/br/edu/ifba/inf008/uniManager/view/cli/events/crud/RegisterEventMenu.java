package br.edu.ifba.inf008.uniManager.view.cli.events.crud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Teacher;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.Exceptions.BadRequestException;
import br.edu.ifba.inf008.uniManager.utils.MenuUtil;

public class RegisterEventMenu {
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
            System.out.println("0. Exit                                    ");
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
        String title = readStringBasicEvent("Title", type);
        String id = readId(type);
        String description = readStringBasicEvent("Description", type);
        LocalDate date = readDate(type);
        String local = readStringBasicEvent("Local", type);
        int capacity = readIntBasicEvent("capacity", type);

        readSpecificsAttributesAndCreate(title, id, description, date, local, capacity, type);
    }

    private void readSpecificsAttributesAndCreate(String title, String id, String description, LocalDate date, String local, int capacity, String type){
        if(type.equals("AcademicFair")){
            try {
                int numberOfStands = readIntBasicEvent("number of stands", type);
                String organizerCpf = readStringBasicEvent("organizer Cpf", type);//TODO: procurar pelo cpf do participante;

                Teacher teacher = new Teacher("paulao", "33410110054", "bla@gmail.com", "cabula", "71982984223", date);
    
                Class<?> clazz = Class.forName(classPath+type);
                Event event = (Event)clazz.getDeclaredConstructor(
                    String.class , String.class, String.class, LocalDate.class, String.class, int.class, int.class, Participant.class
                ).newInstance(
                    title, id, description, date, local, capacity, numberOfStands, teacher
                ); 
    
                eventManager.create(event);
            } catch (Exception e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        }
        if(type.equals("Workshop")){
            try {
                int numberOfMaterials = readIntBasicEvent("number of Materials", type);
                //String organizerCpf = readStringBasicEvent("organizer Cpf", type);//TODO: procurar pelo cpf do participante;

                Teacher instructor = new Teacher("paulao", "33410110054", "bla@gmail.com", "cabula", "71982984223", date);
    
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
                String subject = readStringBasicEvent("subject", type);//TODO: procurar pelo cpf do participante;
                Teacher teacher = new Teacher("paulao", "33410110054", "bla@gmail.com", "cabula", "71982984223", date);
    
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
                String theme = readStringBasicEvent("theme", type);//TODO: procurar pelo cpf do participante;
                Teacher speaker = new Teacher("paulao", "33410110054", "bla@gmail.com", "cabula", "71982984223", date);
    
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

    private String readStringBasicEvent(String element, String type){
        String input;
        System.out.println(MenuUtil.clearTerminal());

        System.out.println("===========================================");
        System.out.println("Insert an "+element+" for the "+type);
        System.out.println("===========================================");
        
        input = scanner.nextLine();
        return input;
    } 

    private String readId(String type){
        int alreadyExist;
        String id;
        do { 
            alreadyExist = 0;

            System.out.println(MenuUtil.clearTerminal());
            
            System.out.println("===========================================");
            System.out.println("Insert an Id for the "+type);
            System.out.println("===========================================");
            
            id = scanner.nextLine();
            
            if(eventManager.get(id) != null){
                MenuUtil.errorScreen("The "+type+" already exists!");
                alreadyExist = 1;
            }
        } while (alreadyExist != 0);
        
        return id;
    }

    private int readIntBasicEvent(String element, String type) {
        int number = -1;
        String line;
        do {
            System.out.println(MenuUtil.clearTerminal());

            System.out.println("===========================================");
            System.out.println("Insert a "+element+" of the "+type);
            System.out.println("===========================================");
        
            line = scanner.nextLine().trim();
            
            try {
                number = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        } while (number == -1);

        return number;
    }

    private LocalDate readDate(String type){
        LocalDate date;
        String line;
        do {
            System.out.println(MenuUtil.clearTerminal());

            System.out.println("===========================================");
            System.out.println("Insert a Date of the "+type);
            System.out.println("format: (dd/MM/yyyy)");
            System.out.println("===========================================");
        
            line = scanner.nextLine().trim();
            
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                date = LocalDate.parse(line, formatter);

            } catch (DateTimeParseException e) {
                MenuUtil.errorScreen(e.getMessage());
                date = LocalDate.MIN;
            }
        } while (date == LocalDate.MIN);

        return date;
    }
}
