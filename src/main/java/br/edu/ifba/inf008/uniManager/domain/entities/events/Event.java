/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.edu.ifba.inf008.uniManager.domain.entities.events;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.utils.exceptions.BadRequestException;

/**
 *
 * @author gabriel
 */
public abstract class Event implements Serializable{
    private static final long serialVersionUID = 1L;
    protected String title;
    protected String id;
    protected String description;
    protected LocalDate date;
    protected String local;
    protected int capacity;
    protected LinkedHashMap<String, Participant> participants = new LinkedHashMap<>();

    public Event(String title, String id, String description, LocalDate date, String local, int capacity){
        this.title = title;
        this.id = id;
        this.description = description;
        this.date = date;
        this.local = local;
        this.capacity = capacity;
    }

    //#region Gets
    public String getTitle(){ return this.title; }
    public String getId(){ return this.id; }
    public String getDescription(){ return this.description; }
    public LocalDate getDate(){ return this.date; }
    public String getLocal(){ return this.local; }
    public int getCapacity(){ return this.capacity; }
    public LinkedHashMap<String, Participant> getParticipants(){ return this.participants; }

    public String getVacancy(){ 
        return String.format("%d/%d", capacity - participants.size(), capacity); 
    }
    //#endregion

    //#region Sets
    public void setTitle(String title){ this.title = title; }
    public void setDescription(String description){ this.description = description; }
    public void setDate(LocalDate date){ this.date = date; }
    public void setLocal(String local){ this.local = local; }
    public void setCapacity(int capacity){ this.capacity = capacity; }
    public void setParticipants(LinkedHashMap<String, Participant> participants){ this.participants = participants; }
    //#endregion

    public abstract String getType();

    public void subscribeParticipant(Participant participant) throws BadRequestException {
        if(participants.size() >= capacity) 
            throw new BadRequestException("It was not possible to register because the event is full");
            
        if(participants.get(participant.getCpf()) != null) 
            throw new BadRequestException("It was not possible to register because the" + participant.getClass().getSimpleName() + "is already participating");

        participants.put(participant.getCpf(), participant);

        participant.addEvent(this.id);
    }
}
