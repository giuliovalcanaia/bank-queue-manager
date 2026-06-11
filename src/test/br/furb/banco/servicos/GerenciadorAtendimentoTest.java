package br.furb.banco.servicos;

import br.furb.banco.modelos.Cliente;
import br.furb.banco.modelos.RegistroAtendimento;
import br.furb.banco.modelos.TipoAtendimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadorAtendimentoTest {

    private GerenciadorAtendimento gerenciador;

    @BeforeEach
    public void setUp() {
        gerenciador = new GerenciadorAtendimento(2, 1);
    }

    @Test
    @DisplayName("Valida alternância guichê geral")
    public void validaAlternanciaGuicheGeral() {
        // Coloca um supressor na saída
        PrintStream consoleOriginal = System.out;
        try {
            System.setOut(new PrintStream(OutputStream.nullOutputStream()));

            gerenciador.adicionarCliente(new Cliente(1, TipoAtendimento.PREFERENCIAL, LocalTime.of(10, 0)));
            gerenciador.adicionarCliente(new Cliente(2, TipoAtendimento.PREFERENCIAL, LocalTime.of(10, 1)));
            gerenciador.adicionarCliente(new Cliente(3, TipoAtendimento.GERAL, LocalTime.of(10, 2)));
            gerenciador.adicionarCliente(new Cliente(4, TipoAtendimento.GERAL, LocalTime.of(10, 3)));

            RegistroAtendimento reg1 = gerenciador.chamarProximo(1, LocalTime.of(10, 5));
            assertEquals(1, reg1.getCliente().getId());
            assertEquals(TipoAtendimento.PREFERENCIAL, reg1.getTipoAtendimento());

            RegistroAtendimento reg2 = gerenciador.chamarProximo(1, LocalTime.of(10, 10));
            assertEquals(3, reg2.getCliente().getId());
            assertEquals(TipoAtendimento.GERAL, reg2.getTipoAtendimento());

            RegistroAtendimento reg3 = gerenciador.chamarProximo(1, LocalTime.of(10, 15));
            assertEquals(2, reg3.getCliente().getId());
            assertEquals(TipoAtendimento.PREFERENCIAL, reg3.getTipoAtendimento());

        } finally {
            System.setOut(consoleOriginal);
        }
    }

    @Test
    @DisplayName("Valida se o guichê preferencial atende apenas prioridad")
    public void validaExclusividadeGuichePreferencial() {
        PrintStream consoleOriginal = System.out;
        try {
            System.setOut(new PrintStream(OutputStream.nullOutputStream()));

            gerenciador.adicionarCliente(new Cliente(1, TipoAtendimento.GERAL, LocalTime.of(10, 0)));
            gerenciador.adicionarCliente(new Cliente(2, TipoAtendimento.GERAL, LocalTime.of(10, 1)));

            RegistroAtendimento reg = gerenciador.chamarProximo(3, LocalTime.of(10, 5));

            assertFalse(gerenciador.getFilaGeral().estaVazia());

        } finally {
            System.setOut(consoleOriginal);
        }
    }

    @Test
    @DisplayName("Deve retornar null ao tentar chamar com todas as filas vazias")
    public void validaChamarFilaVazia() {
        PrintStream consoleOriginal = System.out;
        try {
            System.setOut(new PrintStream(OutputStream.nullOutputStream()));

            RegistroAtendimento reg = gerenciador.chamarProximo(1, LocalTime.of(10, 0));
            assertNull(reg);

        } finally {
            System.setOut(consoleOriginal);
        }
    }
}