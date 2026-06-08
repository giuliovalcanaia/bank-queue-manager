package br.furb.banco;

import br.furb.banco.modelos.Cliente;
import br.furb.banco.servicos.GerenciadorAtendimento;
import br.furb.banco.servicos.RelatorioService;

import java.time.LocalTime;

public class App {

    public static void main(String[] args) {
        System.out.println("Iniciando simulação do posto de atendimento...");

        // 1. Inicializa o gerenciador (que já cria os 3 guichês internamente)
        GerenciadorAtendimento gerenciador = new GerenciadorAtendimento(2, 1);

        // 2. Criação e chegada de objetos clientes para fazer uma simulação
        // Parâmetros: ID, isPrioritario, Horário de Chegada
        System.out.println("Clientes chegando no banco...");
        gerenciador.adicionarCliente(new Cliente(1, false, LocalTime.of(10, 0)));
        gerenciador.adicionarCliente(new Cliente(2, true, LocalTime.of(10, 5)));
        gerenciador.adicionarCliente(new Cliente(3, false, LocalTime.of(10, 10)));
        gerenciador.adicionarCliente(new Cliente(4, true, LocalTime.of(10, 15)));
        gerenciador.adicionarCliente(new Cliente(5, false, LocalTime.of(10, 20)));
        gerenciador.adicionarCliente(new Cliente(6, false, LocalTime.of(10, 25)));

        // 3. Simulação de atendimento pelos guichês
        System.out.println("Guichês iniciando os atendimentos...");

        // Guichê 1 (Preferencial) chama às 10:10. Deve atender o Cliente 2 (Prioritário)
        gerenciador.chamarProximo(1, LocalTime.of(10, 10));

        // Guichê 2 (Geral) chama às 10:16. Como está livre, atende a prioridade: Cliente 4
        gerenciador.chamarProximo(2, LocalTime.of(10, 16));

        // Guichê 3 (Geral) chama às 10:20. Como a fila prioritária está vazia agora, atende o Cliente 1 (Normal)
        gerenciador.chamarProximo(3, LocalTime.of(10, 20));

        // Guichê 2 (Geral) terminou o atendimento anterior e chama de novo às 10:40.
        // Como o último dele foi prioritário (Cliente 4), ele obedece à alternância e atende o Cliente 3 (Normal)
        gerenciador.chamarProximo(2, LocalTime.of(10, 40));

        // Guichê 1 (Preferencial) tenta chamar às 10:45. A fila prioritária está vazia.
        // Pela regra, ele NÃO atende a fila normal e fica ocioso (retorna null).
        gerenciador.chamarProximo(1, LocalTime.of(10, 45));

        // Guichê 3 (Geral) chama às 10:50. Atende o Cliente 5 (Normal)
        gerenciador.chamarProximo(3, LocalTime.of(10, 50));

        // Guichê 2 (Geral) chama às 11:00. Atende o Cliente 6 (Normal)
        gerenciador.chamarProximo(2, LocalTime.of(11, 0));


        // 4. Geração do Relatório Final
        // Exibirá as métricas de tempo, uso de pilhas/filas e aplicará o QuickSort nas duas ordens exigidas
        RelatorioService relatorioService = new RelatorioService();
        relatorioService.imprimirRelatorio(gerenciador);

        System.out.println("Fim da simulação");
    }
}