package br.edu.ifba.inf008.uniManager.utils;

import java.util.Scanner;

public class MenuUtil {
    public static String clearTerminal(){
        return "\033[H\033[2J";
    }
    
    public static void waitAnyInput(){
        new Scanner(System.in).nextLine();
    }

    public static void errorScreen(String message){
        System.out.println(clearTerminal());
        System.out.println("\033[33m"+message+"\033[0m" + " (type anything to continue)");
        waitAnyInput();
    }

    public static boolean exitConfirm(){
        System.out.println(clearTerminal());
        System.out.println("|===========================================|");
        System.out.println("|      Are you sure you want to exit?       |");
        System.out.println("|-------------------------------------------|");
        System.out.println("|              Type 0 to leave              |");
        System.out.println("|===========================================|");

        try {
            String line = new Scanner(System.in).nextLine().trim();
            int choice = Integer.parseInt(line);
            if(choice == 0) return true;
            else return false;
        } catch (Exception e) {
            return false;
        }
    }
}
