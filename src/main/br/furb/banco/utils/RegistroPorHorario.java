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

    /**
     * Compara este registro com outro pelo horário de início do atendimento,
     * em ordem cronológica crescente (atendimento mais antigo primeiro).
     * @param outro O outro RegistroPorHorario a ser comparado.
     * @return Valor negativo se este tiver horário anterior, zero se simultâneos,
     *         ou valor positivo se este tiver horário posterior.
     */
    @Override
    public int compareTo(RegistroPorHorario outro) {
        return this.registro.getHorarioInicioAtendimento().compareTo(outro.registro.getHorarioInicioAtendimento());
    }
}