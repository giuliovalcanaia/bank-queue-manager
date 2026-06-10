package br.furb.banco.modelos;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Classe responsável por armazenar os dados do atendimento de um cliente em um guichê.
 */
public class RegistroAtendimento {

    private Cliente cliente;
    private LocalTime horarioInicioAtendimento;
    private LocalTime horarioTerminoAtendimento;
    private long tempoEspera;
    private long tempoAtendimento;
    private TipoAtendimento tipoAtendimento;

    /**
     * Construtor da classe RegistroAtendimento.
     * @param cliente Objeto cliente que está sendo atendido (contém ID e horário de entrada).
     * @param horarioInicioAtendimento Horário em que o cliente foi chamado no guichê.
     */
    public RegistroAtendimento(Cliente cliente, TipoAtendimento tipoAtendimento, LocalTime horarioInicioAtendimento) {
        this.cliente = cliente;
        this.tipoAtendimento = tipoAtendimento;
        this.horarioInicioAtendimento = horarioInicioAtendimento;
        this.tempoEspera = calculaTempoEsperaMinutos();
        this.tempoAtendimento = calculaTempoAtendimentoMinutos();
    }

    private long calculaTempoEsperaMinutos() {
        Duration duracao = Duration.between(cliente.getHorarioChegada(), horarioInicioAtendimento);
        return duracao.toMinutes();
    }

    private long calculaTempoAtendimentoMinutos() {
        Duration duracao = Duration.between(horarioInicioAtendimento, horarioTerminoAtendimento);
        return duracao.toMinutes();
    }

    // Getters

    public Cliente getCliente() {
        return cliente;
    }

    public LocalTime getHorarioInicioAtendimento() {
        return horarioInicioAtendimento;
    }

    public long getTempoAtendimento() {
        return tempoAtendimento;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public long getTempoEspera() {
        return tempoEspera;
    }

    public LocalTime getHorarioTerminoAtendimento() {
        return horarioTerminoAtendimento;
    }

    // Setters

    public void setHorarioTerminoAtendimento(LocalTime horarioTerminoAtendimento) {
        this.horarioTerminoAtendimento = horarioTerminoAtendimento;
    }

    @Override
    public String toString() {
        return "Registro de atendimento {" +
                " ClienteID = " + cliente.getId() +
                ", Tipo = " + cliente.getTipoAtendimento().getDescricao() +
                ", Chegada = " + cliente.getHorarioChegada() +
                ", Início = " + getHorarioInicioAtendimento() +
                ", Término = " + getHorarioTerminoAtendimento()+
                ", Tempo Espera = " + getTempoEspera() + " min" +
                ", Tempo Atendimento = " + getTempoAtendimento() + " min" +
                " }";
    }
}