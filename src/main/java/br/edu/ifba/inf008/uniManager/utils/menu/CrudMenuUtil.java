package br.edu.ifba.inf008.uniManager.utils.menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.useCase.managers.interfaces.IManager;
import br.edu.ifba.inf008.uniManager.utils.validator.Validator;

public class CrudMenuUtil {
    public static Scanner scanner = new Scanner(System.in);

    public static String readId(IManager<?> manager, String type, String element){
        int repeat = 1;
        int alreadyExist = 0;
        String id;
        do { 
            alreadyExist = 0;

            System.out.println(MenuUtil.clearTerminal());
            
            System.out.println("===========================================");
            System.out.println("Insert an "+element+" for the "+type);
            System.out.println("===========================================");
            
            id = scanner.nextLine();

            if(element.equals("cpf")){
                if(!Validator.validateCpf(id)){
                    MenuUtil.errorScreen("Invalid Cpf!");
                    continue;
                }else{
                    repeat = 0;
                }

            if(manager.get(id) != null){
                    MenuUtil.errorScreen("The "+type+" already exists!");
                    alreadyExist = 1;
                }
            }
        } while (repeat == 1 && alreadyExist != 1);
        
        return id;
    }

    public static int readIntBasic(String element, String type) {
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

    public static LocalDate readDate(String type){
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

    public static String readStringBasic(String element, String type){
        String input;
        System.out.println(MenuUtil.clearTerminal());

        System.out.println("===========================================");
        System.out.println("Insert an "+element+" for the "+type);
        System.out.println("===========================================");
        
        input = scanner.nextLine();
        return input;
    } 
}
