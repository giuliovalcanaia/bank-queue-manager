package br.furb.banco.modelos;

/**
 * Enumeração que representa os tipos possíveis de atendimento
 */
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