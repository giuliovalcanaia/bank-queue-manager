package br.furb.banco.modelos;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

/**
 * Classe responsável por armazenar os dados do atendimento de um cliente em um guichê.
 */
public class RegistroAtendimento {

    private Cliente cliente;
    private LocalTime horarioInicioAtendimento;
    private int tempoAtendimento; // Tempo em minutos

    /**
     * Construtor da classe RegistroAtendimento.
     * @param cliente Objeto cliente que está sendo atendido (contém ID e horário de entrada).
     * @param horarioInicioAtendimento Horário em que o cliente foi chamado no guichê.
     */
    public RegistroAtendimento(Cliente cliente, LocalTime horarioInicioAtendimento) {
        this.cliente = cliente;
        this.horarioInicioAtendimento = horarioInicioAtendimento;
        this.tempoAtendimento = simularTempoAtendimento();
    }

    /**
     * Gera um valor aleatório entre 2 e 30 para o tempo de atendimento.
     * @return Tempo em minutos.
     */
    private int simularTempoAtendimento() {
        Random random = new Random();
        // random.nextInt(29) gera um número de 0 a 28. Somando 2, temos o intervalo de 2 a 30.
        return random.nextInt(29) + 2;
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

    // Métodos de atalho

    public int getIdCliente() {
        return cliente.getId();
    }

    public LocalTime getHorarioEntradaFila() {
        return cliente.getHorarioChegada();
    }

    public  String getPrioridade() {
        if (cliente.isPrioritario()) {
            return "Prioritário";
        }
        return "Não prioritário";
    }

    @Override
    public String toString() {
        return "RegistroAtendimento {" +
                " ClienteID = " + getIdCliente() +
                ", Tipo = " + getPrioridade() +
                ", Chegada = " + getHorarioEntradaFila() +
                ", Início = " + horarioInicioAtendimento +
                ", Tempo Atendimento = " + tempoAtendimento + " min" +
                ", Tempo Espera = " + getTempoEsperaMinutos() + " min" +
                " }";
    }
}