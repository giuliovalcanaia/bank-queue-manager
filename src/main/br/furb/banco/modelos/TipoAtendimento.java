package br.furb.banco.modelos;

public enum TipoAtendimento {

    PREFERENCIAL("Atendimento Preferencial"),
    GERAL("Atendimento Geral");

    private final String descricao;

    TipoAtendimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}