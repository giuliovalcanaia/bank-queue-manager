package br.furb.banco.servicos;

import br.furb.banco.estruturas.pilhas.PilhaLista;
import br.furb.banco.estruturas.ordenacao.OrdenacaoQuickSort;
import br.furb.banco.modelos.Guiche;
import br.furb.banco.modelos.RegistroAtendimento;
import br.furb.banco.utils.RegistroPorHorario;
import br.furb.banco.utils.RegistroPorTempo;

/**
 * Classe responsável por consolidar as métricas e gerar os relatórios do sistema.
 */
public class RelatorioService {

    /**
     * Imprime o relatório completo de atendimentos.
     * @param gerenciador O gerenciador contendo os guichês e os seus históricos.
     */
    public void imprimirRelatorio(GerenciadorAtendimento gerenciador) {
        Guiche[] guiches = gerenciador.getGuiches();

        System.out.println("Relatório de atendimentos");

        int totalGeralAtendimentos = 0;
        int totalGeralNormal = 0;
        int totalGeralPrioritario = 0;
        long tempoEsperaTotalGeral = 0;
        long tempoEsperaTotalNormal = 0;
        long tempoEsperaTotalPrioritario = 0;

        // 1. Calcula o tamanho para o vetor
        for (Guiche g : guiches) {
            totalGeralAtendimentos += g.getHistoricoAtendimentos().tamanho();
        }

        RegistroAtendimento[] todosRegistros = new RegistroAtendimento[totalGeralAtendimentos];
        int indexArray = 0;

        // 2. Coletar dados preservando a pilha original
        for (Guiche guiche : guiches) {
            int totalGuiche = 0;
            int normalGuiche = 0;
            int prioritarioGuiche = 0;

            PilhaLista<RegistroAtendimento> pilhaTemp = new PilhaLista<>();
            PilhaLista<RegistroAtendimento> historico = guiche.getHistoricoAtendimentos();

            while (!historico.estaVazia()) {
                RegistroAtendimento registro = historico.pop();
                pilhaTemp.push(registro);

                todosRegistros[indexArray++] = registro;

                totalGuiche++;
                long tempoEspera = registro.getTempoEsperaMinutos();
                tempoEsperaTotalGeral += tempoEspera;

                if (registro.getCliente().isPrioritario()) {
                    prioritarioGuiche++;
                    totalGeralPrioritario++;
                    tempoEsperaTotalPrioritario += tempoEspera;
                } else {
                    normalGuiche++;
                    totalGeralNormal++;
                    tempoEsperaTotalNormal += tempoEspera;
                }
            }

            // Restaura o histórico
            while (!pilhaTemp.estaVazia()) {
                historico.push(pilhaTemp.pop());
            }

            System.out.println("Guichê " + guiche.getId() + " (" + guiche.getTipo().getDescricao() + "):");
            System.out.println("  - Total de atendimentos: " + totalGuiche);
            System.out.println("  - Normais: " + normalGuiche + " | Prioritários: " + prioritarioGuiche);
            System.out.println("-------------------------------------------------");
        }

        // 3. Métricas Globais
        System.out.println("MÉTRICAS DE DESEMPENHO GLOBAIS:");
        System.out.println("Total Geral de Atendimentos: " + totalGeralAtendimentos);

        if (totalGeralAtendimentos > 0) {
            System.out.println("Tempo Médio de Espera Total: " + (tempoEsperaTotalGeral / totalGeralAtendimentos) + " min");
        } else {
            System.out.println("Tempo Médio de Espera Total: 0 min");
        }

        if (totalGeralPrioritario > 0) {
            System.out.println("Tempo Médio (Prioritários): " + (tempoEsperaTotalPrioritario / totalGeralPrioritario) + " min");
        } else {
            System.out.println("Tempo Médio (Prioritários): 0 min");
        }

        if (totalGeralNormal > 0) {
            System.out.println("Tempo Médio (Normais): " + (tempoEsperaTotalNormal / totalGeralNormal) + " min");
        } else {
            System.out.println("Tempo Médio (Normais): 0 min");
        }

        // 4. Executa as ordenações utilizando o QuickSort fornecido
        if (totalGeralAtendimentos > 0) {
            imprimirOrdenacoes(todosRegistros);
        }
    }

    /**
     * Imprime duas listas: a ordem de atendimentos por tempo de espera
     * e por ordem cronológica
     * @param registros O array RegistroAtendimento[] contendo os registros feitos
     *                  ao longo do dia.
     */
    private void imprimirOrdenacoes(RegistroAtendimento[] registros) {

        // --- ORDENAÇÃO 1: POR TEMPO DE ESPERA ---
        System.out.println("RELAÇÃO DE ATENDIMENTOS (Ordem Crescente de Tempo de Espera):");

        RegistroPorTempo[] arrayTempo = new RegistroPorTempo[registros.length];
        for (int i = 0; i < registros.length; i++) {
            arrayTempo[i] = new RegistroPorTempo(registros[i]);
        }

        OrdenacaoQuickSort<RegistroPorTempo> quickTempo = new OrdenacaoQuickSort<>();
        quickTempo.setInfo(arrayTempo);
        quickTempo.ordenar();

        for (RegistroPorTempo rt : arrayTempo) {
            System.out.println(rt.getRegistro().toString());
        }

        // --- ORDENAÇÃO 2: POR ORDEM CRONOLÓGICA ---
        System.out.println("RELAÇÃO DE ATENDIMENTOS (Ordem Cronológica - Horário Atendimento):");

        RegistroPorHorario[] arrayHorario = new RegistroPorHorario[registros.length];
        for (int i = 0; i < registros.length; i++) {
            arrayHorario[i] = new RegistroPorHorario(registros[i]);
        }

        OrdenacaoQuickSort<RegistroPorHorario> quickHorario = new OrdenacaoQuickSort<>();
        quickHorario.setInfo(arrayHorario);
        quickHorario.ordenar();

        for (RegistroPorHorario rh : arrayHorario) {
            System.out.println(rh.registro.toString());
        }
    }

}