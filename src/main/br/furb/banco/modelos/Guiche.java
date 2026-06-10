package br.furb.banco.modelos;

import br.furb.banco.estruturas.pilhas.*;

/**
 * Classe que representa um Guichê no sistema de atendimento.
 */
public class Guiche {

    private int id;
    private TipoAtendimento tipoAtendimento;
    private PilhaLista<RegistroAtendimento> historicoAtendimentos;
    private boolean estavaAtendendo;
    private int qtdAtendimentosTotal;
    private int qtdAtendimentosPrioritario;
    private int qtdAtendimentosGeral;

    /**
     * Construtor do Guichê.
     * * @param id   Identificador do guichê (ex: 1, 2, 3)
     * @param tipo Tipo do guichê (ex: TipoAtendimento.PREFERENCIAL, TipoAtendimento.GERAL)
     */
    public Guiche(int id, TipoAtendimento tipo) {
        this.id = id;
        this.tipoAtendimento = tipo;
        this.historicoAtendimentos = new PilhaLista<>();
        this.estavaAtendendo = false;
    }

    /**
     * Registra o atendimento empilhando-o no histórico e atualizando as métricas
     * * @param registro O registro do atendimento recém-finalizado.
     */
    public void registrarAtendimento(RegistroAtendimento registro) {
        // Empilha o registro na estrutura
        this.historicoAtendimentos.push(registro);

        // Calcula as métricas
        this.qtdAtendimentosTotal++;
        if (registro.getTipoAtendimento() == TipoAtendimento.GERAL) {
            this.qtdAtendimentosGeral++;
        } else {
            this.qtdAtendimentosPrioritario++;
        }

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

    public int getQtdAtendimentosTotal() {
        return qtdAtendimentosTotal;
    }

    public int getQtdAtendimentosPrioritario() {
        return qtdAtendimentosPrioritario;
    }

    public int getQtdAtendimentosGeral() {
        return qtdAtendimentosGeral;
    }

    public boolean estavaAtendendo() {
        return estavaAtendendo;
    }

    // Setters

    public void setEstavaAtendendo(boolean estavaAtendendo) {
        this.estavaAtendendo = estavaAtendendo;
    }

    @Override
    public String toString() {
        return "Guiche { " +
                "ID = " + id +
                ", Tipo = " + tipoAtendimento.getDescricao() +
                ", Histórico (Tamanho) = " + historicoAtendimentos.tamanho() +
                ", Quantidade de atendimentos geral = " + this.qtdAtendimentosGeral +
                ", Quantidade de atendimentos prioritário = " + this.qtdAtendimentosPrioritario +
                ", Quantidade total de atendimentos = " + this.qtdAtendimentosTotal +
                " }";
    }
}