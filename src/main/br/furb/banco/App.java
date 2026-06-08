package br.furb.banco;

import br.furb.banco.modelos.Cliente;
import br.furb.banco.servicos.GerenciadorAtendimento;
import br.furb.banco.servicos.RelatorioService;

import java.time.LocalTime;

public class App {

    public static void main(String[] args) {
        System.out.println("Iniciando simulação do posto de atendimento...");

        // 1. Inicializa o gerenciador e criar os guichês
        GerenciadorAtendimento gerenciador = new GerenciadorAtendimento(2, 1);

        // ORDEM CRONOLÓGICA DOS EVENTOS
        // 2. Criação e chegada de objetos clientes
        // 3. Simultaneamente, faz a simulação de atendimento pelos guichês
        gerenciador.adicionarCliente(new Cliente(1, false, LocalTime.of(10, 0)));
        gerenciador.adicionarCliente(new Cliente(2, true, LocalTime.of(10, 5)));
        gerenciador.adicionarCliente(new Cliente(3, false, LocalTime.of(10, 10)));
        gerenciador.chamarProximo(1, LocalTime.of(10, 10));
        gerenciador.adicionarCliente(new Cliente(4, true, LocalTime.of(10, 15)));
        gerenciador.chamarProximo(2, LocalTime.of(10, 16));
        gerenciador.adicionarCliente(new Cliente(5, false, LocalTime.of(10, 20)));
        gerenciador.chamarProximo(3, LocalTime.of(10, 21));
        gerenciador.adicionarCliente(new Cliente(6, false, LocalTime.of(10, 25)));
        gerenciador.chamarProximo(2, LocalTime.of(10, 40));
        gerenciador.chamarProximo(1, LocalTime.of(10, 45));
        gerenciador.chamarProximo(3, LocalTime.of(10, 50));
        gerenciador.chamarProximo(2, LocalTime.of(11, 0));
        // Tenta chamar mais um mesmo sem fila
        gerenciador.chamarProximo(1, LocalTime.of(11, 10));
        // Chega mais um cliente e então o guiche 1 chama
        gerenciador.adicionarCliente(new Cliente(7, false, LocalTime.of(11, 25)));
        gerenciador.chamarProximo(1, LocalTime.of(11, 25));



        // 4. Geração do Relatório Final
        // Exibirá as métricas de tempo, uso de pilhas/filas e aplicará o QuickSort nas duas ordens exigidas
        RelatorioService relatorioService = new RelatorioService();
        relatorioService.imprimirRelatorio(gerenciador);

        System.out.println("Fim da simulação");
    }
}