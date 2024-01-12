package com.example.clinicaconsultas;

import java.io.Serializable;

public class Consulta implements Serializable {
    private Paciente paciente;
    private String dia;
    private String hora;
    private String especialidade;

    public Paciente getPaciente() {
        return paciente;
    }



    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }



    public String getDia() {
        return dia;
    }



    public void setDia(String dia) {
        this.dia = dia;
    }



    public String getHora() {
        return hora;
    }



    public void setHora(String hora) {
        this.hora = hora;
    }



    public String getEspecialidade() {
        return especialidade;
    }



    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }



    @Override
    public String toString() {
        return "Paciente: " + paciente + ", Dia: " + dia + ", Hora: " + hora + ", Especialidade: " + especialidade;
    }
}
