package br.furb.banco.modelos;

import java.time.LocalTime;

/**
 * Classe que representa um Cliente no sistema de atendimento bancário.
 */
public class Cliente {

    private int id;
    private boolean prioritario;
    private LocalTime horarioChegada;

    /**
     * Construtor da classe Cliente.
     * * @param id             Identificador único do cliente.
     * @param prioritario    Define se o cliente é da fila de prioridade (true) ou normal (false).
     * @param horarioChegada Horário em que o cliente entrou na fila.
     */
    public Cliente(int id, boolean prioritario, LocalTime horarioChegada) {
        this.id = id;
        this.prioritario = prioritario;
        this.horarioChegada = horarioChegada;
    }

    // Getters

    public int getId() {
        return id;
    }

    public boolean isPrioritario() {
        return prioritario;
    }

    public LocalTime getHorarioChegada() {
        return horarioChegada;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }

    public void setHorarioChegada(LocalTime horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    @Override
    public String toString() {
        String tipo = "Normal";
        if (prioritario) {
            tipo = "Prioritário";
        }

        return "Cliente { " +
                "ID = " + id +
                ", Tipo = " + tipo +
                ", Chegada = " + horarioChegada +
                " }";
    }
}