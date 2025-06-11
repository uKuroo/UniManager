package br.edu.ifba.inf008.uniManager.domain.entities.participants;

import java.io.Serializable;
import java.time.LocalDate;

public class Teacher extends Participant implements Serializable{

    public Teacher(String name, String cpf, String email, String address, String phone, LocalDate birthDate){
        super(name, cpf, email, address, phone, birthDate);
    }

    @Override
    public String getType(){
        return "Teacher";
    }
}
