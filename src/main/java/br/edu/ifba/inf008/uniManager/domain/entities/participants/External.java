package br.edu.ifba.inf008.uniManager.domain.entities.participants;

import java.time.LocalDate;

public class External extends Participant{
    private static final long serialVersionUID = 1L;
    public String organization;
    public String role;

    public External(String name, String cpf, String email, String address, String phone, LocalDate birthDate, String organization, String role){
        super(name, cpf, email, address, phone, birthDate);
        this.organization = organization;
        this.role = role;
    }

     public String getOrganization(){ return this.organization; }
     public String getRole(){ return this.role; }

    @Override
    public String getType(){
        return "External";
    }
}
