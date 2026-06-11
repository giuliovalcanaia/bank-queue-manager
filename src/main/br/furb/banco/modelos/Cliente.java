package br.furb.banco.modelos;

import java.time.LocalTime;

/**
 * Classe que representa um Cliente no sistema de atendimento bancário.
 */
public class Cliente {

    private int id;
    private TipoAtendimento tipoAtendimento;
    private LocalTime horarioChegada;

    public Cliente(int id, TipoAtendimento tipoAtendimento, LocalTime horarioChegada) {
        this.id = id;
        this.tipoAtendimento = tipoAtendimento;
        this.horarioChegada = horarioChegada;
    }

    // Getters

    public int getId() {
        return id;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public LocalTime getHorarioChegada() {
        return horarioChegada;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public void setHorarioChegada(LocalTime horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    @Override
    public String toString() {
        return "Cliente ID = " + getId() +
                "\nTipo = " + getTipoAtendimento().getDescricao() +
                "\nChegada = " + getHorarioChegada();
    }
}