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
    private Guiche guiche;

    /**
     * Construtor da classe RegistroAtendimento.
     * @param cliente Objeto cliente que está sendo atendido (contém ID e horário de entrada).
     * @param horarioInicioAtendimento Horário em que o cliente foi chamado no guichê.
     */
    public RegistroAtendimento(Cliente cliente, TipoAtendimento tipoAtendimento, LocalTime horarioInicioAtendimento, Guiche guiche) {
        this.cliente = cliente;
        this.tipoAtendimento = tipoAtendimento;
        this.horarioInicioAtendimento = horarioInicioAtendimento;
        this.tempoEspera = calculaTempoEsperaMinutos();
        this.guiche = guiche;
        this.tempoAtendimento = -1;
    }

    private long calculaTempoEsperaMinutos() {
        Duration duracao = Duration.between(cliente.getHorarioChegada(), horarioInicioAtendimento);
        return duracao.toMinutes();
    }

    public long calculaTempoAtendimentoMinutos() {
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

    public void setTempoAtendimento(long tempoAtendimento) {
        this.tempoAtendimento = tempoAtendimento;
    }

    @Override
    public String toString() {
        return "Registro de atendimento" +
                "\nClienteID = " + cliente.getId() +
                "\nTipo = " + cliente.getTipoAtendimento().getDescricao() +
                "\nChegada = " + cliente.getHorarioChegada() +
                "\nInício = " + getHorarioInicioAtendimento() +
                "\nTérmino = " + getHorarioTerminoAtendimento()+
                "\nTempo Espera = " + getTempoEspera() + " min" +
                "\nTempo Atendimento = " + calculaTempoAtendimentoMinutos() + " min" +
                "\n-----------------------------------------------------------";
    }
}