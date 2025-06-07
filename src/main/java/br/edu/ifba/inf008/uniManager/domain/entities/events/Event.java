/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.edu.ifba.inf008.uniManager.domain.entities.events;

import java.time.LocalDate;
import java.util.HashMap;

import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;

/**
 *
 * @author gabriel
 */
public abstract class Event {
    protected String title;
    protected String id;
    protected String description;
    protected LocalDate date;
    protected String local;
    protected int capacity;
    protected HashMap<String, Participant> participants;


    public Event(String title, String id, String description, LocalDate date, String local, int capacity){
        this.title = title;
        this.id = id;
        this.description = description;
        this.date = date;
        this.local = local;
        this.capacity = capacity;
        this.participants = new HashMap<>();
    }

    //#region Gets
    public String getTitle(){ return this.title; }
    public String getId(){ return this.id; }
    public String getDescription(){ return this.description; }
    public LocalDate getDate(){ return this.date; }
    public String getLocal(){ return this.local; }
    public int getCapacity(){ return this.capacity; }
    public HashMap<String, Participant> getParticipants(){ return this.participants; }
    //#endregion

    //#region Sets
    public void setTitle(String title){ this.title = title; }
    public void setDescription(String description){ this.description = description; }
    public void setDate(LocalDate date){ this.date = date; }
    public void setLocal(String local){ this.local = local; }
    public void setCapacity(int capacity){ this.capacity = capacity; }
    public void setParticipants(HashMap<String, Participant> participants){ this.participants = participants; }
    //#endregion
}
