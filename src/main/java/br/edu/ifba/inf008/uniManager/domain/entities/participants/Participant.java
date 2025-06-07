/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.edu.ifba.inf008.uniManager.domain.entities.participants;

/**
 *
 * @author gabriel
 */
public abstract class Participant {
    protected String cpf;
    protected String name;

    public String getCpf(){
        return this.cpf;
    }
}
