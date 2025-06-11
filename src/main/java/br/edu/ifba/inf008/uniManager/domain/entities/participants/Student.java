package br.edu.ifba.inf008.uniManager.domain.entities.participants;

import java.io.Serializable;
import java.time.LocalDate;

public class Student extends Participant implements Serializable{
    private static final long serialVersionUID = 1L;
    private final String registration;
    private final String instituition;

    public Student(String name, String cpf, String email, String address, String phone, LocalDate birthDate, String registration, String instituition){
        super(name, cpf, email, address, phone, birthDate);
        this.registration = registration;
        this.instituition = instituition;
    }

    public String getRegistration(){ return this.registration; }
    public String getInstituition(){ return this.instituition; }

    @Override
    public String getType(){
        return "Student";
    }
}
