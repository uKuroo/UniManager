package br.edu.ifba.inf008.uniManager.domain.entities.participants;

import java.io.Serializable;
import java.time.LocalDate;

public class Teacher extends Participant implements Serializable{
    private final String internalRegistration;
    private final String department;

    public Teacher(String name, String cpf, String email, String address, String phone, LocalDate birthDate, String department, String internalRegistration){
        super(name, cpf, email, address, phone, birthDate);
        this.department = department;
        this.internalRegistration = internalRegistration;
    }

    @Override
    public String getType(){
        return "Teacher";
    }
}
