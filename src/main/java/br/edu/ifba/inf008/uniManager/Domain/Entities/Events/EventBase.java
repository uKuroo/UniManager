/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.edu.ifba.inf008.uniManager.Domain.Entities.Events;

import java.util.Date;
import java.util.HashMap;

import br.edu.ifba.inf008.uniManager.Domain.Entities.Persons.PersonBase;

/**
 *
 * @author gabriel
 */
public abstract class EventBase {
    protected String title;
    protected String description;
    protected Date date;
    protected String local;
    protected int capacity;
    protected HashMap<String, PersonBase> participants;


    public EventBase(String title, String description, Date date, String local, int capacity){
        this.title = title;
        this.description = description;
        this.date = date;
        this.local = local;
        this.capacity = capacity;
        this.participants = new HashMap<>();
    }

    public String getTitle(){ return this.title; }
    public String getDescription(){ return this.description; }
    public Date getDate(){ return this.date; }
    public String getLocal(){ return this.local; }
    public int getCapacity(){ return this.capacity; }
    public HashMap<String, PersonBase> getParticipants(){ return this.participants; }

    public void setTitle(String title){ this.title = title; }
    public void setDescription(String description){ this.description = description; }
    public void setDate(Date date){ this.date = date; }
    public void setLocal(String local){ this.local = local; }
    public void setCapacity(int capacity){ this.capacity = capacity; }
    public void setParticipants(HashMap<String, PersonBase> participants){ this.participants = participants; }
    
}
