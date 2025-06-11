package br.edu.ifba.inf008.uniManager.view.cli.participants.crud;

import java.time.LocalDate;
import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.exceptions.BadRequestException;
import br.edu.ifba.inf008.uniManager.utils.menu.CrudMenuUtil;
import br.edu.ifba.inf008.uniManager.utils.menu.MenuUtil;
import br.edu.ifba.inf008.uniManager.view.cli.IMenu;

public class RegisterParticipantMenu implements IMenu{
    private final EventManager eventManager;
    private final ParticipantManager participantManager;
    private final Scanner scanner;
    private final String classPath = "br.edu.ifba.inf008.uniManager.domain.entities.participants.";
    
    public RegisterParticipantMenu(EventManager eventManager, ParticipantManager participantManager){
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
            System.out.println("Select a type of Participant to register");
            System.out.println("===========================================");
            System.out.println("1. Student                                 ");
            System.out.println("2. Teacher                                 ");
            System.out.println("3. External                                ");
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
                        showStepScreen("Student");
                        break;
                    case 2: 
                        showStepScreen("Teacher");
                        break;
                    case 3: 
                        showStepScreen("External");
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
        String name = CrudMenuUtil.readStringBasic("name", type);
        String cpf = CrudMenuUtil.readId(participantManager, type);
        String email = CrudMenuUtil.readStringBasic("email", type);
        String address = CrudMenuUtil.readStringBasic("address", type);
        String phone = CrudMenuUtil.readStringBasic("phone", type);
        LocalDate birthDate = CrudMenuUtil.readDate(type);

        readSpecificsAttributesAndCreate(name, cpf, email, address, phone, birthDate, type);
    }

    private void readSpecificsAttributesAndCreate(String name, String cpf, String email, String address, String phone, LocalDate birthDate, String type){
        if(type.equals("Student")){
            try {
                String registration = CrudMenuUtil.readStringBasic("registration", type);
                String instituition = CrudMenuUtil.readStringBasic("instituition", type);
    
                Class<?> clazz = Class.forName(classPath+type);
                Participant participant = (Participant)clazz.getDeclaredConstructor(
                    String.class , String.class, String.class, String.class, String.class, LocalDate.class, String.class, String.class
                ).newInstance(
                    name, cpf, email, address, phone, birthDate, registration, instituition
                ); 
    
                participantManager.create(participant);
            } catch (Exception e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        }
        if(type.equals("Teacher")){
            try {
                String internalRegistration = CrudMenuUtil.readStringBasic("Internal Registration", type);
                String department = CrudMenuUtil.readStringBasic("Department", type);
    
                Class<?> clazz = Class.forName(classPath+type);
                Participant participant = (Participant)clazz.getDeclaredConstructor(
                    String.class , String.class, String.class, String.class, String.class, LocalDate.class, String.class, String.class
                ).newInstance(
                    name, cpf, email, address, phone, birthDate, department, internalRegistration
                ); 
    
                participantManager.create(participant);
            } catch (Exception e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        }
        if(type.equals("External")){
            try {
                String organization = CrudMenuUtil.readStringBasic("Organization", type);
                String role = CrudMenuUtil.readStringBasic("Role", type);
    
                Class<?> clazz = Class.forName(classPath+type);
                Participant participant = (Participant)clazz.getDeclaredConstructor(
                    String.class , String.class, String.class, String.class, String.class, LocalDate.class, String.class, String.class
                ).newInstance(
                    name, cpf, email, address, phone, birthDate, organization, role
                ); 
    
                participantManager.create(participant);
            } catch (Exception e) {
                MenuUtil.errorScreen(e.getMessage());
            }
        }
    }
}
