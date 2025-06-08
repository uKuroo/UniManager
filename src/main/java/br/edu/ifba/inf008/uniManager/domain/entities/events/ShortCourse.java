package br.edu.ifba.inf008.uniManager.domain.entities.events;

import java.time.LocalDate;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;

public class ShortCourse extends Event{
    private Participant teacher;
    private String subject;

    public ShortCourse(String title, String id, String description, LocalDate date, String local, int capacity, Participant teacher, String subject){
        super(title, id, description, date, local, capacity);
        this.teacher = teacher;
        this.subject = subject;
    }

    public Participant getTeacher(){ return this.teacher; }
    public String getSubject(){ return this.subject; }

    public void setTeacher(Participant teacher){ this.teacher = teacher; } //TODO: aceitar apenas professor ou externo
    public void setSubject(String subject){ this.subject = subject; }

    @Override
    public String getType(){
        return "ShortCourse";
    }
}
