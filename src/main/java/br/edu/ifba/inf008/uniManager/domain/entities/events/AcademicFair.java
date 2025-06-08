package br.edu.ifba.inf008.uniManager.domain.entities.events;

import java.time.LocalDate;
import java.util.ArrayList;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;

public class AcademicFair extends Event{
    private int numberOfStands;
    private ArrayList<Participant> organizers;

    public AcademicFair(String title, String id, String description, LocalDate date, String local, int capacity, int numberOfStands, ArrayList<Participant> organizers){
        super(title, id, description, date, local, capacity);
        this.numberOfStands = numberOfStands;
        this.organizers = organizers;
    }

    public ArrayList<Participant> getOrganizers(){ return this.organizers; }
    public int getNumberOfStands(){ return this.numberOfStands; }

    public void setNumberOfStands(int numberOfStands){ this.numberOfStands = numberOfStands; }
    public void setOrganizers(ArrayList<Participant> organizers){ this.organizers = organizers; }

    @Override
    public String getType(){
        return "AcademicFair";
    }
}