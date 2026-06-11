package br.furb.banco.servicos;

import br.furb.banco.modelos.Cliente;
import br.furb.banco.modelos.TipoAtendimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RelatorioTest {

    private GerenciadorAtendimento gerenciador;
    private Relatorio relatorio;

    @BeforeEach
    public void setUp() {
        gerenciador = new GerenciadorAtendimento(2, 1);

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
        gerenciador.chamarProximo(1, LocalTime.of(11, 10));

        gerenciador.adicionarCliente(new Cliente(7, TipoAtendimento.GERAL, LocalTime.of(11, 25)));
        gerenciador.chamarProximo(1, LocalTime.of(11, 25));

        gerenciador.chamarProximo(1, LocalTime.of(11, 30));
        gerenciador.chamarProximo(2, LocalTime.of(11, 30));
        gerenciador.chamarProximo(3, LocalTime.of(11, 30));

        relatorio = new Relatorio(gerenciador);

        relatorio.calculaQtdAtendimentosGlobal();
        relatorio.calculaTempoEsperaGlobal();
    }

    @Test
    @DisplayName("Valida métricas fila geral")
    public void validaMetricasFilaGeral() {
        assertEquals(4, relatorio.qtdAtendimentosGeralGlobal);

        assertEquals(28.75, relatorio.tempoEsperaMedioGeralGlobal, 0.01);
    }

    @Test
    @DisplayName("Valida métricas fila prioritária")
    public void testMetricasFilaPrioritaria() {
        assertEquals(3, relatorio.qtdAtendimentosPrioritarioGlobal);

        assertEquals(10.33, relatorio.tempoEsperaMedioPrioritarioGlobal, 0.01);
    }

    @Test
    @DisplayName("Valida métricas globais")
    public void testMetricasGlobaisTotais() {
        assertEquals(7, relatorio.qtdAtendimentosTotalGlobal);

        assertEquals(20.85, relatorio.tempoEsperaMedioTotalGlobal, 0.01);
    }
}