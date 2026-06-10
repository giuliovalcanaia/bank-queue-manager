package br.furb.banco;

import br.furb.banco.modelos.Cliente;
import br.furb.banco.modelos.TipoAtendimento;
import br.furb.banco.servicos.GerenciadorAtendimento;
import br.furb.banco.servicos.Relatorio;

import java.time.LocalTime;

public class App {

    public static void main(String[] args) {
        System.out.println("----------------------------------------------");
        System.out.println("Iniciando simulação do posto de atendimento...");
        System.out.println("----------------------------------------------");

        GerenciadorAtendimento gerenciador = new GerenciadorAtendimento(2, 1);

        // Ordem cronológica dos evendos
        gerenciador.adicionarCliente(new Cliente(1, TipoAtendimento.GERAL, LocalTime.of(10, 0)));
        gerenciador.adicionarCliente(new Cliente(2, TipoAtendimento.PREFERENCIAL, LocalTime.of(10, 5)));
        gerenciador.adicionarCliente(new Cliente(3, TipoAtendimento.GERAL, LocalTime.of(10, 10)));
        gerenciador.chamarProximo(1, LocalTime.of(10, 10));
        gerenciador.adicionarCliente(new Cliente(4, TipoAtendimento.PREFERENCIAL, LocalTime.of(10, 15)));
        gerenciador.chamarProximo(2, LocalTime.of(10, 16));
        gerenciador.adicionarCliente(new Cliente(5, TipoAtendimento.GERAL, LocalTime.of(10, 20)));
        gerenciador.chamarProximo(3, LocalTime.of(10, 21));
        gerenciador.adicionarCliente(new Cliente(6, TipoAtendimento.PREFERENCIAL, LocalTime.of(10, 25)));
        gerenciador.chamarProximo(2, LocalTime.of(10, 40));
        gerenciador.chamarProximo(1, LocalTime.of(10, 45));
        gerenciador.chamarProximo(3, LocalTime.of(10, 50));
        gerenciador.chamarProximo(2, LocalTime.of(11, 0));
        // Tenta chamar mais um mesmo sem fila
        gerenciador.chamarProximo(1, LocalTime.of(11, 10));
        // Chega mais um cliente e então o guiche 1 chama
        gerenciador.adicionarCliente(new Cliente(7, TipoAtendimento.GERAL, LocalTime.of(11, 25)));
        gerenciador.chamarProximo(1, LocalTime.of(11, 25));
        gerenciador.chamarProximo(1, LocalTime.of(11, 30));
        gerenciador.chamarProximo(2, LocalTime.of(11, 30));
        gerenciador.chamarProximo(3, LocalTime.of(11, 30));

        // Gera o Relatório Final
        Relatorio relatorioService = new Relatorio(gerenciador);
        relatorioService.imprimirRelatorio();

        System.out.println("Fim da simulação");
    }
}