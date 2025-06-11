package br.edu.ifba.inf008.uniManager.domain.entities.events;

import java.io.Serializable;
import java.time.LocalDate;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;

public class Lecture extends Event implements Serializable{
    private Participant speaker;
    private String theme;

    public Lecture(String title, String id, String description, LocalDate date, String local, int capacity, Participant speaker, String theme){
        super(title, id, description, date, local, capacity);
        this.speaker = speaker;
        this.theme = theme;
    }

    public Participant getSpeaker() { return speaker; }
    public String getTheme(){ return this.theme; }

    public void setTheme(String theme){ this.theme = theme; }
    public void setSpeaker(Participant speaker){ this.speaker = speaker; }

    @Override
    public String getType(){
        return "Lecture";
    }
}
