/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.edu.ifba.inf008.uniManager.domain.entities.participants;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;

/**
 *
 * @author gabriel
 */
public abstract class Participant implements Serializable{
    protected String name;
    protected String cpf;
    protected String email;
    protected String address;
    protected String phone;
    protected LocalDate birthDate;
    protected ArrayList<String> eventsIdIncluded = new ArrayList<>();

    public Participant(String name, String cpf, String email, String address, String phone, LocalDate birthDate){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    //#region Gets
    public String getName(){ return this.name; }
    public String getCpf(){ return this.cpf; }
    public String getEmail(){ return this.email; }
    public String getphone(){ return this.phone; }
    public LocalDate getBirthDate(){ return this.birthDate; }
    public ArrayList<String> getEventsIdIncluded(){ return this.eventsIdIncluded; }
    //#endregion 
    
    //#region Sets
    public void setName(String name){ this.name = name; }
    public void setCpf(String cpf){ this.cpf = cpf; }
    public void setEmail(String email){ this.email = email; }
    public void setphone(String phone){ this.phone = phone; }
    public void setBirthDate(LocalDate birthDate){ this.birthDate = birthDate; }
    //#endregion
    
    public void addEvent(String id){
        eventsIdIncluded.add(id);
    }

    public abstract String getType();
}
