package br.edu.ifba.inf008.uniManager.utils;

import java.util.Scanner;

public class MenuUtil {
    public static String clearTerminal(){
        return "\033[H\033[2J";
    }
    
    public static void waitAnyInput(){
        new Scanner(System.in).nextLine();
    }
}
