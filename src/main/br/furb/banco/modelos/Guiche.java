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
    private  double somaTempoEsperaGeral;
    private double somaTempoEsperaPrioritario;

    public Guiche(int id, TipoAtendimento tipo) {
        this.id = id;
        this.tipoAtendimento = tipo;
        this.historicoAtendimentos = new PilhaLista<>();
    }

    public void registrarAtendimento(RegistroAtendimento registro) {

        // Contador de qtd e soma tempo de atendimentos geral
        if (registro.getTipoAtendimento() == TipoAtendimento.GERAL) {
            this.somaTempoEsperaGeral += registro.getTempoEspera();
            this.qtdAtendimentosGeral++;
        // Contador de qtd e soma tempo de atendimentos prioritário
        } else {
            this.somaTempoEsperaPrioritario += registro.getTempoEspera();
            this.qtdAtendimentosPrioritario++;
        }
        this.qtdAtendimentosTotal++;
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

    public double getSomaTempoEsperaGeral() {
        return somaTempoEsperaGeral;
    }

    public double getSomaTempoEsperaPrioritario() {
        return somaTempoEsperaPrioritario;
    }

    public double getTempoEsperaMedioGeral() {
        // teste para evitar divisão por zero
        if (qtdAtendimentosGeral > 0) {
            return somaTempoEsperaGeral / qtdAtendimentosGeral;
        }
        return 0;
    }

    public double getTempoEsperaMedioPrioritario() {
        if (qtdAtendimentosPrioritario > 0) {
            return somaTempoEsperaPrioritario / qtdAtendimentosPrioritario;
        }
        return 0;
    }

    public double getTempoEsperaMedioTotal() {
        if (qtdAtendimentosTotal > 0) {
            return (somaTempoEsperaGeral + somaTempoEsperaPrioritario) / qtdAtendimentosTotal;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Guiche ID = " + id +
                "\nTipo = " + tipoAtendimento.getDescricao() +
                "\nHistórico (Tamanho) = " + historicoAtendimentos.tamanho() +
                "\nQuantidade de atendimentos geral = " + this.qtdAtendimentosGeral +
                "\nQuantidade de atendimentos prioritário = " + this.qtdAtendimentosPrioritario +
                "\nQuantidade total de atendimentos = " + this.qtdAtendimentosTotal +
                "\nTempo médio de atendimentos geral " + getTempoEsperaMedioGeral() + " min" +
                "\nTempo médio de atendimentos prioritários " + getTempoEsperaMedioPrioritario() + " min" +
                "\nTempo médio total dos atendimentos " + getTempoEsperaMedioTotal() + " min" +
                "\n-----------------------------------------------------------";
    }
}