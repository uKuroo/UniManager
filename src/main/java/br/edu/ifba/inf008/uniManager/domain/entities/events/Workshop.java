package br.edu.ifba.inf008.uniManager.domain.entities.events;

import java.time.LocalDate;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;

public class Workshop extends Event{
    private Participant instructor;
    private boolean materialNeeded;

    public Workshop(String title, String id, String description, LocalDate date, String local, int capacity, Participant instructor, boolean materialNeeded){
        super(title, id, description, date, local, capacity);
        this.instructor = instructor;
        this.materialNeeded = materialNeeded;
    }

    public Participant getInstructor(){ return this.instructor; }
    public boolean needMaterial(){ return this.materialNeeded; }
    
    public void setInstructor(Participant instructor){ this.instructor = instructor; }
    public void setMaterialNeed(boolean materialNeeded){ this.materialNeeded = materialNeeded; }

    @Override
    public String getType(){
        return "Workshop";
    }
}
