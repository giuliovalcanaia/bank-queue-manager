package br.furb.banco.utils;

import br.furb.banco.modelos.RegistroAtendimento;

public class RegistroPorHorario implements Comparable<RegistroPorHorario> {
    public RegistroAtendimento registro;

    public RegistroPorHorario(RegistroAtendimento registro) {
        this.registro = registro;
    }

    public RegistroAtendimento getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroAtendimento registro) {
        this.registro = registro;
    }

    @Override
    public int compareTo(RegistroPorHorario outro) {
        return this.registro.getHorarioInicioAtendimento().compareTo(outro.registro.getHorarioInicioAtendimento());
    }
}
