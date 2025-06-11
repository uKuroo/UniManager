package br.edu.ifba.inf008.uniManager.domain.entities.events;

import java.io.Serializable;
import java.time.LocalDate;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Student;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Teacher;
import br.edu.ifba.inf008.uniManager.utils.Exceptions.BadRequestException;

public class ShortCourse extends Event implements Serializable{
    private Teacher teacher;
    private String subject;

    public ShortCourse(String title, String id, String description, LocalDate date, String local, int capacity, Teacher teacher, String subject){
        super(title, id, description, date, local, capacity);
        this.teacher = teacher;
        this.subject = subject;
    }

    public Teacher getTeacher(){ return this.teacher; }
    public String getSubject(){ return this.subject; }

    public void setTeacher(Teacher teacher){ this.teacher = teacher; }
    public void setSubject(String subject){ this.subject = subject; }

    @Override
    public String getType(){
        return "ShortCourse";
    }

    @Override
    public void subscribeParticipant(Participant participant){
        if(!(participant instanceof Student))
            throw new BadRequestException("Only students can participate in courses.");

        super.subscribeParticipant(participant);
    }
}
