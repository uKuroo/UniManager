/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package br.edu.ifba.inf008.uniManager.Domain.Controllers.Events.Interface;

import java.util.HashMap;

import br.edu.ifba.inf008.uniManager.Domain.Entities.Events.EventBase;
import br.edu.ifba.inf008.uniManager.Domain.Entities.Persons.PersonBase;

/**
 *
 * @author gabriel
 */
public interface IEventController {
    public abstract void addParticipant(PersonBase person);
    public abstract HashMap<String, PersonBase> listParticipants(EventBase event);
}
