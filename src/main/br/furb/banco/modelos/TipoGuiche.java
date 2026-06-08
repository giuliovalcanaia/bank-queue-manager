package br.furb.banco.modelos;

/**
 * Enumeração que define os tipos de guichês disponíveis no posto de atendimento.
 */
public enum TipoGuiche {

    /**
     * Guichê destinado exclusivamente ao atendimento de clientes da FilaPrioridade.
     */
    PREFERENCIAL("Atendimento Preferencial"),

    /**
     * Guichê destinado ao atendimento alternado entre FilaPrioridade e FilaNormal.
     */
    GERAL("Atendimento Geral");

    private final String descricao;

    /**
     * Construtor do enum.
     * @param descricao Nome legível do tipo de guichê.
     */
    TipoGuiche(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição do tipo de guichê.
     * @return Descrição em formato String.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna a descrição formatada em caso de uso de System.out.println()
     * @return Descrição formatada ao invés do texto em caixa alta
     */
    @Override
    public String toString() {
        return this.descricao;
    }
}