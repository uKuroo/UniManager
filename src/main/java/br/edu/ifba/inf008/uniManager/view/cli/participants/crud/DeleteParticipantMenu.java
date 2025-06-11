package br.edu.ifba.inf008.uniManager.view.cli.participants.crud;

import java.util.Scanner;

import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.EventManager;
import br.edu.ifba.inf008.uniManager.useCase.managers.implementation.ParticipantManager;
import br.edu.ifba.inf008.uniManager.utils.menu.MenuUtil;

public class DeleteParticipantMenu {
    private final EventManager eventManager;
    private final ParticipantManager participantManager;
    private final Scanner scanner;
    
    public DeleteParticipantMenu(EventManager eventManager, ParticipantManager participantManager){
        this.eventManager = eventManager;
        this.participantManager = participantManager;
        this.scanner = new Scanner(System.in);
    }

    public boolean showConfirmation(String id){
        System.out.println(MenuUtil.clearTerminal());
        System.out.println("|===========================================|");
        System.out.println("|     Are you sure you want to delete?      |");
        System.out.println("|-------------------------------------------|");
        System.out.println("|           Type 'del' to delete            |");
        System.out.println("|===========================================|");

        String line = new Scanner(System.in).nextLine().trim();
            
        if(line.equals("del")){
            participantManager.delete(id);

            MenuUtil.successScreen("Participant was deleted with success!");

            return true;
        }
        return false;
    }
}
