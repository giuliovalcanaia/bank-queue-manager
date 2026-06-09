package br.furb.banco.estruturas.pilhas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PilhaListaTest{

    private PilhaLista<Integer> pilha;

    @BeforeEach
    void setUp() {
        // Inicializa uma nova pilha antes de cada teste
        pilha = new PilhaLista<>();
    }

    @Test
    @DisplayName("Caso 1: Conferir se o método estaVazia reconhece pilha vazia")
    void validaEstaVaziaReconhecePilhaVazia() {
        assertTrue(pilha.estaVazia());
    }

    @Test
    @DisplayName("Caso 2: Conferir se o método estaVazia reconhece pilha não vazia")
    void validaEstaVaziaReconhecePilhaNaoVazia() {
        pilha.push(10);

        assertFalse(pilha.estaVazia());
    }

    @Test
    @DisplayName("Caso 3: Conferir se os dados são empilhados e desempilhados corretamente")
    void validaEmpilharEDesempilharCorretamente() {
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);

        assertEquals(30, pilha.pop());
        assertEquals(20, pilha.pop());
        assertEquals(10, pilha.pop());

        assertTrue(pilha.estaVazia());
    }

    @Test
    @DisplayName("Caso 4: Conferir se o método peek() retorna o topo da pilha")
    void validaPeekRetornaTopoDaPilha() {
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);

        assertEquals(30, pilha.peek());
        assertEquals(30, pilha.pop());
    }

    @Test
    @DisplayName("Caso 5: Conferir se o método liberar() remove os elementos da pilha")
    void validaLiberarRemoveElementosDaPilha() {
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);

        pilha.liberar();

        assertTrue(pilha.estaVazia());
    }
}