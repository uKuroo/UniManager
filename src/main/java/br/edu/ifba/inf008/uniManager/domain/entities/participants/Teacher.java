package br.edu.ifba.inf008.uniManager.domain.entities.participants;

import java.io.Serializable;
import java.time.LocalDate;

public class Teacher extends Participant implements Serializable{
    private static final long serialVersionUID = 1L;
    private final String internalRegistration;
    private final String department;

    public Teacher(String name, String cpf, String email, String address, String phone, LocalDate birthDate, String department, String internalRegistration){
        super(name, cpf, email, address, phone, birthDate);
        this.department = department;
        this.internalRegistration = internalRegistration;
    }

    public String getInternalRegistration(){ return this.internalRegistration; }
    public String getDepartment(){ return this.department; }
    
    @Override
    public String getType(){
        return "Teacher";
    }
}
