package br.furb.banco.modelos;

import br.furb.banco.estruturas.pilhas.*;

/**
 * Classe que representa um Guichê no sistema de atendimento.
 */
public class Guiche {

    private int id;
    private TipoAtendimento tipoAtendimento;
    private PilhaLista<RegistroAtendimento> historicoAtendimentos;
    private int qtdAtendimentosTotal;
    private int qtdAtendimentosPrioritario;
    private int qtdAtendimentosGeral;
    private double tempoEsperaMedioGeral;
    private double tempoEsperaMedioPrioritario;
    private double tempoEsperaMedioTotal;

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
     * Registra o atendimento empilhando-o no histórico e atualizando as métricas
     * * @param registro O registro do atendimento recém-finalizado.
     */
    public void registrarAtendimento(RegistroAtendimento registro) {

        // Calcula as médias de tempo
        long tempoEsperaDesteAtendimento = registro.getTempoEspera();
        if (registro.getTipoAtendimento() == TipoAtendimento.GERAL) {
            // Média ponderada
            this.tempoEsperaMedioGeral = (this.tempoEsperaMedioGeral * this.qtdAtendimentosGeral + tempoEsperaDesteAtendimento) / (this.qtdAtendimentosGeral + 1);
        } else {
            // Média ponderada
            this.tempoEsperaMedioPrioritario = (this.tempoEsperaMedioPrioritario * this.qtdAtendimentosPrioritario + tempoEsperaDesteAtendimento) / (this.qtdAtendimentosPrioritario + 1);
        }

        // Calcula as métricas quantitativas
        this.qtdAtendimentosTotal++;
        if (registro.getTipoAtendimento() == TipoAtendimento.GERAL) {
            this.qtdAtendimentosGeral++;
        } else {
            this.qtdAtendimentosPrioritario++;
        }

        // Média total
        this.tempoEsperaMedioTotal = (this.tempoEsperaMedioGeral * qtdAtendimentosGeral + this.tempoEsperaMedioPrioritario * qtdAtendimentosPrioritario) / (qtdAtendimentosTotal);

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

    public int getQtdAtendimentosTotal() {
        return qtdAtendimentosTotal;
    }

    public int getQtdAtendimentosPrioritario() {
        return qtdAtendimentosPrioritario;
    }

    public int getQtdAtendimentosGeral() {
        return qtdAtendimentosGeral;
    }

    public double getTempoEsperaMedioGeral() {
        return tempoEsperaMedioGeral;
    }

    public double getTempoEsperaMedioPrioritario() {
        return tempoEsperaMedioPrioritario;
    }

    public double getTempoEsperaMedioTotal() {
        return tempoEsperaMedioTotal;
    }

    @Override
    public String toString() {
        return "Guiche ID = " + id +
                "\nTipo = " + tipoAtendimento.getDescricao() +
                "\nHistórico (Tamanho) = " + historicoAtendimentos.tamanho() +
                "\nQuantidade de atendimentos geral = " + this.qtdAtendimentosGeral +
                "\nQuantidade de atendimentos prioritário = " + this.qtdAtendimentosPrioritario +
                "\nQuantidade total de atendimentos = " + this.qtdAtendimentosTotal +
                "\nTempo médio de atendimentos geral " + this.tempoEsperaMedioGeral + " min" +
                "\nTempo médio de atendimentos prioritários " + this.tempoEsperaMedioPrioritario + " min" +
                "\nTempo médio total dos atendimentos " + this.tempoEsperaMedioTotal +
                "\n-----------------------------------------------------------";
    }
}