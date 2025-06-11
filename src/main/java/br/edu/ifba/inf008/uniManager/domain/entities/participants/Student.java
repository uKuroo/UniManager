/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.edu.ifba.inf008.uniManager.domain.entities.participants;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author gabriel
 */
public class Student extends Participant implements Serializable{
    private final String registration;

    public Student(String name, String cpf, String email, String address, String phone, LocalDate birthDate, String registration){
        super(name, cpf, email, address, phone, birthDate);
        this.registration = registration;
    }

    public String getRegistration(){ return this.registration; }

    @Override
    public String getType(){
        return "Student";
    }
}
