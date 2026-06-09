package br.furb.banco.estruturas.filas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilaListaTest {

    private Fila<Integer> fila;

    @BeforeEach
    void setUp() {
        // Inicializa uma nova fila antes de cada teste
        fila = new FilaLista<>();
    }

    @Test
    @DisplayName("Caso 1: Conferir se o método estaVazia reconhece fila vazia")
    void validaEstaVaziaReconheceFilaVazia() {
        assertTrue(fila.estaVazia());
    }

    @Test
    @DisplayName("Caso 2: Conferir se o método estaVazia reconhece fila não vazia")
    void validaEstaVaziaReconheceFilaNaoVazia() {
        fila.inserir(10);

        assertFalse(fila.estaVazia());
    }

    @Test
    @DisplayName("Caso 3: Conferir se os dados são enfileirados e desenfileirados corretamente")
    void validaEnfileirarEDesenfileirarCorretamente() {
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);

        assertEquals(10, fila.retirar());
        assertEquals(20, fila.retirar());
        assertEquals(30, fila.retirar());

        assertTrue(fila.estaVazia());
    }

    @Test
    @DisplayName("Caso 4: Conferir se o método peek() retorna o início da fila")
    void validaPeekRetornaInicioDaFila() {
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);

        assertEquals(10, fila.peek());
        assertEquals(10, fila.retirar());
    }

    @Test
    @DisplayName("Caso 5: Conferir se o método liberar() remove os elementos da fila")
    void validaLiberarRemoveElementosDaFila() {
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);

        fila.liberar();

        assertTrue(fila.estaVazia());
    }
}