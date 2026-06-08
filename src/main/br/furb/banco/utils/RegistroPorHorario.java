package br.furb.banco.utils;

import br.furb.banco.modelos.RegistroAtendimento;

/**
 * Classe auxiliar (wrapper) utilizada para ordenação de registros de atendimento
 * pelo critério cronológico, ou seja, pelo horário de início do atendimento no guichê.
 * Implementa Comparable para permitir o uso com algoritmos de ordenação
 * genéricos, como o OrdenacaoQuickSort.
 */
public class RegistroPorHorario implements Comparable<RegistroPorHorario> {

    public RegistroAtendimento registro;

    /**
     * Construtor da classe RegistroPorHorario.
     * @param registro O registro de atendimento a ser encapsulado para ordenação.
     */
    public RegistroPorHorario(RegistroAtendimento registro) {
        this.registro = registro;
    }

    /**
     * Retorna o registro de atendimento encapsulado.
     * @return O objeto RegistroAtendimento associado a este wrapper.
     */
    public RegistroAtendimento getRegistro() {
        return registro;
    }

    /**
     * Define o registro de atendimento encapsulado.
     * @param registro O novo objeto RegistroAtendimento a ser associado.
     */
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