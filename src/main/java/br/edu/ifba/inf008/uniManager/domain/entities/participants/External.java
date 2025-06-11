package br.edu.ifba.inf008.uniManager.domain.entities.participants;

import java.time.LocalDate;

public class External extends Participant{
    public String organization;
    public String role;

    public External(String name, String cpf, String email, String address, String phone, LocalDate birthDate, String organization, String role){
        super(name, cpf, email, address, phone, birthDate);
        this.organization = organization;
        this.role = role;
    }

    @Override
    public String getType(){
        return "External";
    }
}
