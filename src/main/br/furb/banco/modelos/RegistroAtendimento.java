package br.furb.banco.modelos;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Classe responsável por armazenar os dados do atendimento de um cliente em um guichê.
 */
public class RegistroAtendimento {

    private Cliente cliente;
    private LocalTime horarioInicioAtendimento;
    private int tempoAtendimento;

    /**
     * Construtor da classe RegistroAtendimento.
     * @param cliente Objeto cliente que está sendo atendido (contém ID e horário de entrada).
     * @param horarioInicioAtendimento Horário em que o cliente foi chamado no guichê.
     */
    public RegistroAtendimento(Cliente cliente, LocalTime horarioInicioAtendimento, int tempoAtendimento) {
        this.cliente = cliente;
        this.horarioInicioAtendimento = horarioInicioAtendimento;
        this.tempoAtendimento = tempoAtendimento;
    }

    /**
     * Calcula o tempo de espera do cliente na fila.
     * Faz a diferença entre o horário de atendimento e o horário de chegada.
     * @return O tempo de espera em minutos.
     */
    public long getTempoEsperaMinutos() {
        if (cliente == null || cliente.getHorarioChegada() == null || horarioInicioAtendimento == null) {
            return 0;
        }
        // Classe duration é usada para medir o tempo entre dois instantes
        Duration duracao = Duration.between(cliente.getHorarioChegada(), horarioInicioAtendimento);
        return duracao.toMinutes();
    }

    // Getters

    public Cliente getCliente() {
        return cliente;
    }

    public LocalTime getHorarioInicioAtendimento() {
        return horarioInicioAtendimento;
    }

    public int getTempoAtendimento() {
        return tempoAtendimento;
    }

    @Override
    public String toString() {
        return "Registro de atendimento {" +
                " ClienteID = " + cliente.getId() +
                ", Tipo = " + cliente.getTipoAtendimento().getDescricao() +
                ", Chegada = " + cliente.getHorarioChegada() +
                ", Início = " + horarioInicioAtendimento +
                ", Tempo Atendimento = " + tempoAtendimento + " min" +
                ", Tempo Espera = " + getTempoEsperaMinutos() + " min" +
                " }";
    }
}