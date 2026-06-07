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
    @DisplayName("Caso 1: Conferir se o método estaVazia() reconhece fila vazia")
    void testeEstaVaziaReconheceFilaVazia() {
        assertTrue(fila.estaVazia(), "Uma fila recém-criada deve retornar true para estaVazia()");
    }

    @Test
    @DisplayName("Caso 2: Conferir se o método estaVazia() reconhece fila não vazia")
    void testeEstaVaziaReconheceFilaNaoVazia() {
        fila.inserir(10);

        assertFalse(fila.estaVazia(), "Após inserir um elemento, estaVazia() deve retornar false");
    }

    @Test
    @DisplayName("Caso 3: Conferir se os dados são enfileirados e desenfileirados corretamente")
    void testeEnfileirarEDesenfileirarCorretamente() {
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);

        assertEquals(10, fila.retirar(), "O primeiro elemento retirado deve ser 10");
        assertEquals(20, fila.retirar(), "O segundo elemento retirado deve ser 20");
        assertEquals(30, fila.retirar(), "O terceiro elemento retirado deve ser 30");

        assertTrue(fila.estaVazia(), "Após retirar todos os elementos, a fila deve estar vazia");
    }

    @Test
    @DisplayName("Caso 4: Conferir se o método peek() retorna o início da fila")
    void testePeekRetornaInicioDaFila() {
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);

        assertEquals(10, fila.peek(), "peek() deve retornar o primeiro elemento inserido (10)");
        assertEquals(10, fila.retirar(), "retirar() após o peek() também deve retornar 10, sem alterar a ordem");
    }

    @Test
    @DisplayName("Caso 5: Conferir se o método liberar() remove os elementos da fila")
    void testeLiberarRemoveElementosDaFila() {
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);

        fila.liberar();

        assertTrue(fila.estaVazia(), "Após invocar liberar(), a fila deve estar vazia");
    }
}