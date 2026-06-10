package br.furb.banco.utils;

import br.furb.banco.modelos.RegistroAtendimento;

public class RegistroPorHorario implements Comparable<RegistroPorHorario> {
    private RegistroAtendimento registro;

    public RegistroPorHorario(RegistroAtendimento registro) {
        this.registro = registro;
    }

    public RegistroAtendimento getRegistro() {
        return registro;
    }

    @Override
    public int compareTo(RegistroPorHorario outro) {
        // Ordenação pelo horário de início do atendimento
        // A classe LocalTime do Java já implementa Comparable
        return this.registro.getHorarioInicioAtendimento().compareTo(outro.registro.getHorarioInicioAtendimento());
    }
}