package br.furb.banco.servicos;

import br.furb.banco.estruturas.pilhas.PilhaLista;
import br.furb.banco.estruturas.ordenacao.OrdenacaoQuickSort;
import br.furb.banco.modelos.Guiche;
import br.furb.banco.modelos.RegistroAtendimento;
import br.furb.banco.modelos.TipoAtendimento;
import br.furb.banco.utils.RegistroPorHorario;
import br.furb.banco.utils.RegistroPorTempo;

/**
 * Classe responsável por consolidar as métricas e gerar os relatórios do sistema.
 */
public class Relatorio {

    /**
     * Imprime o relatório completo de atendimentos.
     * @param gerenciador O gerenciador contendo os guichês e os seus históricos.
     */
    public void imprimirRelatorio(GerenciadorAtendimento gerenciador) {
        Guiche[] guiches = gerenciador.getGuiches();

        System.out.println("---------------------------------------------------------");
        System.out.println("Relatório de atendimentos");
        System.out.println("---------------------------------------------------------");

        int totalGeralAtendimentos = 0;
        int totalGeralNormal = 0;
        int totalGeralPrioritario = 0;
        long tempoEsperaTotalGeral = 0;
        long tempoEsperaTotalNormal = 0;
        long tempoEsperaTotalPrioritario = 0;

        // Calcula o tamanho para o vetor
        for (Guiche g : guiches) {
            totalGeralAtendimentos += g.getHistoricoAtendimentos().tamanho();
        }

        RegistroAtendimento[] todosRegistros = new RegistroAtendimento[totalGeralAtendimentos];
        int indexArray = 0;

        // Coletar dados preservando a pilha original
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

                if (registro.getCliente().getTipoAtendimento() == TipoAtendimento.PREFERENCIAL) {
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

            System.out.println("Guichê " + guiche.getId() + " (" + guiche.getTipoAtendimento().getDescricao() + "):");
            System.out.println("  - Total de atendimentos: " + totalGuiche);
            System.out.println("  - Geral: " + normalGuiche + " | Prioritários: " + prioritarioGuiche);
            System.out.println("-------------------------------------------------");
        }

        // 3. Métricas Globais
        System.out.println("MÉTRICAS DE DESEMPENHO GLOBAIS:");
        System.out.println("Total Geral de Atendimentos: " + totalGeralAtendimentos);

        if (totalGeralAtendimentos > 0) {
            System.out.println("Tempo Médio de Espera Total: " + ((double) tempoEsperaTotalGeral / totalGeralAtendimentos) + " min");
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

        // Executa as ordenações utilizando o QuickSort
        if (totalGeralAtendimentos > 0) {
            imprimirOrdenacoes(todosRegistros);
        }
    }

    private void imprimirOrdenacoes(RegistroAtendimento[] registros) {

        // Tempo de espera
        System.out.println("RELAÇÃO DE ATENDIMENTOS (Ordem Crescente de Tempo de Espera):");

        // Cria o array que será usado para ordenar
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

        // Ordem cronológica
        System.out.println("RELAÇÃO DE ATENDIMENTOS (Ordem Cronológica - Horário Atendimento):");

        RegistroPorHorario[] arrayHorario = new RegistroPorHorario[registros.length];
        for (int i = 0; i < registros.length; i++) {
            arrayHorario[i] = new RegistroPorHorario(registros[i]);
        }

        OrdenacaoQuickSort<RegistroPorHorario> quickHorario = new OrdenacaoQuickSort<>();
        quickHorario.setInfo(arrayHorario);
        quickHorario.ordenar();

        for (RegistroPorHorario rh : arrayHorario) {
            System.out.println(rh.getRegistro().toString());
        }
    }

}