package br.furb.banco.modelos;

import br.furb.banco.estruturas.pilhas.*;

/**
 * Classe que representa um Guichê no sistema de atendimento.
 */
public class Guiche {

    private int id;
    private TipoAtendimento tipoAtendimento;
    private PilhaLista<RegistroAtendimento> historicoAtendimentos;

    /**
     * Construtor do Guichê.
     * * @param id   Identificador do guichê (ex: 1, 2, 3)
     * @param tipo Tipo do guichê (ex: TipoAtendimento.PREFERENCIAL, TipoAtendimento.GERAL)
     */
    public Guiche(int id, TipoAtendimento tipo) {
        this.id = id;
        this.tipoAtendimento = tipo;
        this.historicoAtendimentos = new PilhaLista<>();
    }

    /**
     * Registra o atendimento empilhando-o no histórico e atualizando a flag de alternância.
     * * @param registro O registro do atendimento recém-finalizado.
     */
    public void registrarAtendimento(RegistroAtendimento registro) {
        // Empilha o registro na estrutura
        this.historicoAtendimentos.push(registro);
    }

    // Getters

    public int getId() {
        return id;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public PilhaLista<RegistroAtendimento> getHistoricoAtendimentos() {
        return historicoAtendimentos;
    }

    @Override
    public String toString() {
        return "Guiche { " +
                "ID = " + id +
                ", Tipo = " + tipoAtendimento.getDescricao() +
                ", Histórico (Tamanho) = " + historicoAtendimentos.tamanho() +
                " }";
    }
}