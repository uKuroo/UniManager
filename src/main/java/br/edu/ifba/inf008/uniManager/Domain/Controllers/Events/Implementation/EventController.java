/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.edu.ifba.inf008.uniManager.Domain.Controllers.Events.Implementation;

import java.util.HashMap;

import br.edu.ifba.inf008.uniManager.Domain.Controllers.Events.Interface.IEventController;
import br.edu.ifba.inf008.uniManager.Domain.Entities.Events.EventBase;
import br.edu.ifba.inf008.uniManager.Domain.Entities.Persons.PersonBase;
import br.edu.ifba.inf008.uniManager.Domain.Entities.Persons.Student;

/**
 *
 * @author gabriel
 */
public class EventController implements IEventController{
    public void addParticipant(PersonBase person){
        Student student = (Student)person;
    }

    public HashMap<String, PersonBase> listParticipants(EventBase event){
        return event.getParticipants();
    }
}
