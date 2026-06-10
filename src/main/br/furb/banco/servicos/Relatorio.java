package br.furb.banco.servicos;

import br.furb.banco.estruturas.ordenacao.OrdenacaoQuickSort;
import br.furb.banco.modelos.Guiche;
import br.furb.banco.modelos.RegistroAtendimento;
import br.furb.banco.utils.RegistroPorHorario;
import br.furb.banco.utils.RegistroPorTempo;

public class Relatorio {
    private GerenciadorAtendimento gerenciadorAtendimento;
    int qtdAtendimentosTotalGlobal = 0;
    int qtdAtendimentosGeralGlobal = 0;
    int qtdAtendimentosPrioritarioGlobal = 0;
    double tempoEsperaMedioGeralGlobal = 0;
    double tempoEsperaMedioPrioritarioGlobal = 0;
    double tempoEsperaMedioTotalGlobal = 0;

    public Relatorio(GerenciadorAtendimento gerenciadorAtendimento) {
        this.gerenciadorAtendimento = gerenciadorAtendimento;
    }

    public void calculaTempoEsperaGlobal() {
        Guiche[] guiches = gerenciadorAtendimento.getGuiches();

        for (Guiche g : guiches) {
            this.tempoEsperaMedioGeralGlobal += Math.round(g.getTempoEsperaMedioGeral() * 100.0) / 100.0;
            this.tempoEsperaMedioPrioritarioGlobal += Math.round(g.getTempoEsperaMedioPrioritario() * 100.0) / 100.0;
            this.tempoEsperaMedioTotalGlobal += Math.round(g.getTempoEsperaMedioTotal() * 100.0) / 100.0;
        }
    }

    private void calculaQtdAtendimentosGlobal() {
        Guiche[] guiches = gerenciadorAtendimento.getGuiches();

        // Por meio do laço faz a soma global de atendimentos
        for (Guiche g : guiches) {
            this.qtdAtendimentosTotalGlobal += g.getQtdAtendimentosTotal();
            this.qtdAtendimentosGeralGlobal += g.getQtdAtendimentosGeral();
            this.qtdAtendimentosPrioritarioGlobal += g.getQtdAtendimentosPrioritario();
        }
    }

    public void imprimirRelatorio() {
        Guiche[] guiches = gerenciadorAtendimento.getGuiches();

        // Saída do relatório
        System.out.println("---------------------------------------------------------");
        System.out.println("Relatório de atendimentos");
        System.out.println("---------------------------------------------------------");

        System.out.println("---------------------------------------------------------");
        System.out.println("Métricas por guichê");
        System.out.println("---------------------------------------------------------");
        for (Guiche g : guiches) {
        // Imprime relatório individual do guichê
        System.out.println(g.toString());
    }

        // Métricas Globais
        System.out.println("---------------------------------------------------------");
        System.out.println("Métricas globais");
        System.out.println("---------------------------------------------------------");
        // Faz o cálculo
        calculaQtdAtendimentosGlobal();
        calculaTempoEsperaGlobal();
        System.out.println("Fila geral");
        System.out.println("Quantidade de atendimentos realizados: " + this.qtdAtendimentosGeralGlobal);
        System.out.println("Tempo médio de espera na fila: " + this.tempoEsperaMedioGeralGlobal + " min");
        System.out.println("\nFila prioritária");
        System.out.println("Quantidade de atendimentos realizados: " + this.qtdAtendimentosPrioritarioGlobal);
        System.out.println("Tempo médio de espera na fila: " + this.tempoEsperaMedioPrioritarioGlobal + " min" );
        System.out.println("\nTotal das duas filas");
        System.out.println("Quantidade de atendimentos realizados: " + this.qtdAtendimentosTotalGlobal);
        System.out.println("Tempo médio de espera na fila: " + this.tempoEsperaMedioTotalGlobal + " min" );

        // Converte a Pilha num vetor antes de ordenar por TEMPO
        RegistroAtendimento[] vetorHistoricoCompleto = new RegistroAtendimento[qtdAtendimentosTotalGlobal];
        int idx = 0;
        // Enqunto não estiver vazio
        while (!gerenciadorAtendimento.getHistoricoCompleto().estaVazia()) {
            vetorHistoricoCompleto[idx] = gerenciadorAtendimento.getHistoricoCompleto().pop();
            idx++;
        }

        // Cria dois vetores: por tempo e por horário
        RegistroPorTempo[] vetorPorTempo = new RegistroPorTempo[qtdAtendimentosTotalGlobal];
        RegistroPorHorario[] vetorPorHorario = new RegistroPorHorario[qtdAtendimentosTotalGlobal];

        // Preenche os vetores
        for (int i = 0; i < qtdAtendimentosTotalGlobal; i++) {
            vetorPorTempo[i] = new RegistroPorTempo(vetorHistoricoCompleto[i]);
            vetorPorHorario[i] = new RegistroPorHorario(vetorHistoricoCompleto[i]);
        }

        // Ordena por tempo
        OrdenacaoQuickSort<RegistroPorTempo> quickTempo = new OrdenacaoQuickSort<>();
        quickTempo.setInfo(vetorPorTempo);
        quickTempo.ordenar();

        // Imprime por tempo (ordem crescente)
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Registro de atendimentos por tempo de espera");
        System.out.println("-----------------------------------------------------------");
        for (RegistroPorTempo rt : vetorPorTempo) {
            System.out.println(rt.getRegistro().toString());
        }

        // Ordena por horário
        OrdenacaoQuickSort<RegistroPorHorario> quickHorario = new OrdenacaoQuickSort<>();
        quickHorario.setInfo(vetorPorHorario);
        quickHorario.ordenar();

        // Imprime por horário (ordem cronológica)
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Registro de atendimentos por ordem de início de atendimento");
        System.out.println("-----------------------------------------------------------");
        for (RegistroPorHorario rh : vetorPorHorario) {
            System.out.println(rh.getRegistro().toString());
        }
    }
}