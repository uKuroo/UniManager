package br.edu.ifba.inf008.uniManager.domain.entities.events;

import java.io.Serializable;
import java.time.LocalDate;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;

public class AcademicFair extends Event implements Serializable{
    private static final long serialVersionUID = 1L;
    private int numberOfStands;
    private Participant organizer;

    public AcademicFair(String title, String id, String description, LocalDate date, String local, int capacity, int numberOfStands, Participant organizer){
        super(title, id, description, date, local, capacity);
        this.numberOfStands = numberOfStands;
        this.organizer = organizer;
    }

    public Participant getOrganizer(){ return this.organizer; }
    public int getNumberOfStands(){ return this.numberOfStands; }

    public void setNumberOfStands(int numberOfStands){ this.numberOfStands = numberOfStands; }
    public void setOrganizer(Participant organizer){ this.organizer = organizer; }

    @Override
    public String getType(){
        return "AcademicFair";
    }
}