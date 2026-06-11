package br.furb.banco.servicos;

import br.furb.banco.modelos.Cliente;
import br.furb.banco.modelos.Guiche;
import br.furb.banco.modelos.TipoAtendimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RelatorioTest {

    private GerenciadorAtendimento gerenciador;
    private Relatorio relatorio;

    @BeforeEach
    public void setUp() {

        // Suprime a saída do console
        PrintStream consoleOriginal = System.out;
        try {
            System.setOut(new PrintStream(OutputStream.nullOutputStream()));

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

        } finally {
            System.setOut(consoleOriginal);
        }
    }

    @Test
    @DisplayName("Valida métricas fila geral")
    public void validaMetricasFilaGeral() {
        assertEquals(4, relatorio.getQtdAtendimentosGeralGlobal());
        assertEquals(28.75, relatorio.getTempoEsperaMedioGeralGlobal(), 0.01);
    }

    @Test
    @DisplayName("Valida métricas fila prioritária")
    public void testMetricasFilaPrioritaria() {
        assertEquals(3, relatorio.getQtdAtendimentosPrioritarioGlobal());
        assertEquals(10.33, relatorio.getTempoEsperaMedioPrioritarioGlobal(), 0.01);
    }

    @Test
    @DisplayName("Valida métricas globais")
    public void testMetricasGlobaisTotais() {
        assertEquals(7, relatorio.getQtdAtendimentosTotalGlobal());
        assertEquals(20.85, relatorio.getTempoEsperaMedioTotalGlobal(), 0.01);
    }

    @Test
    @DisplayName("Valida métricas individuais - Guichê 1")
    public void testMetricasIndividuaisGuiche1() {
        Guiche guiche1 = gerenciador.getGuiches()[0];

        assertEquals(1, guiche1.getId());
        assertEquals(3, guiche1.getQtdAtendimentosTotal());

        assertEquals(1, guiche1.getQtdAtendimentosPrioritario());
        assertEquals(5.0, guiche1.getTempoEsperaMedioPrioritario(), 0.01);

        assertEquals(2, guiche1.getQtdAtendimentosGeral());
        assertEquals(17.5, guiche1.getTempoEsperaMedioGeral(), 0.01);
    }

    @Test
    @DisplayName("Valida métricas individuais - Guichê 2")
    public void testMetricasIndividuaisGuiche2() {
        Guiche guiche2 = gerenciador.getGuiches()[1];

        assertEquals(2, guiche2.getId());
        assertEquals(3, guiche2.getQtdAtendimentosTotal());

        assertEquals(1, guiche2.getQtdAtendimentosPrioritario());
        assertEquals(1.0, guiche2.getTempoEsperaMedioPrioritario(), 0.01);

        assertEquals(2, guiche2.getQtdAtendimentosGeral());
        assertEquals(40.0, guiche2.getTempoEsperaMedioGeral(), 0.01);
    }

    @Test
    @DisplayName("Valida métricas individuais - Guichê 3")
    public void testMetricasIndividuaisGuiche3() {
        Guiche guiche3 = gerenciador.getGuiches()[2];

        assertEquals(3, guiche3.getId());
        assertEquals(1, guiche3.getQtdAtendimentosTotal());

        assertEquals(1, guiche3.getQtdAtendimentosPrioritario());
        assertEquals(25.0, guiche3.getTempoEsperaMedioPrioritario(), 0.01);

        assertEquals(0, guiche3.getQtdAtendimentosGeral());
        assertEquals(0.0, guiche3.getTempoEsperaMedioGeral(), 0.01);
    }

}