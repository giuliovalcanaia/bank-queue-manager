package br.furb.banco.estruturas.pilhas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PilhaListaTest{

    private PilhaLista<Integer> pilha;

    @BeforeEach
    void setUp() {
        // Inicializa uma nova pilha dinâmica antes de cada teste
        pilha = new PilhaLista<>();
    }

    @Test
    @DisplayName("Caso 1: Conferir se o método estaVazia() reconhece pilha vazia")
    void testeEstaVaziaReconhecePilhaVazia() {
        assertTrue(pilha.estaVazia(), "Ao invocar estaVazia() em uma pilha recém-criada, deve resultar em true");
    }

    @Test
    @DisplayName("Caso 2: Conferir se o método estaVazia() reconhece pilha não vazia")
    void testeEstaVaziaReconhecePilhaNaoVazia() {
        pilha.push(10);

        assertFalse(pilha.estaVazia(), "Após empilhar o número 10, estaVazia() deve resultar em false");
    }

    @Test
    @DisplayName("Caso 3: Conferir se os dados são empilhados e desempilhados corretamente")
    void testeEmpilharEDesempilharCorretamente() {
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);

        assertEquals(30, pilha.pop(), "Desempilhar o primeiro dado deve retornar 30");
        assertEquals(20, pilha.pop(), "Desempilhar o segundo dado deve retornar 20");
        assertEquals(10, pilha.pop(), "Desempilhar o terceiro dado deve retornar 10");

        assertTrue(pilha.estaVazia(), "Após desempilhar todos os dados, o método estaVazia() deve resultar em true");
    }

    @Test
    @DisplayName("Caso 4: Conferir se o método peek() retorna o topo da pilha")
    void testePeekRetornaTopoDaPilha() {
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);

        assertEquals(30, pilha.peek(), "Conferir o topo da pilha deve retornar 30");
        assertEquals(30, pilha.pop(), "Retirar o último elemento da pilha deve resultar em 30");
    }

    @Test
    @DisplayName("Caso 5: Conferir se o método liberar() remove os elementos da pilha")
    void testeLiberarRemoveElementosDaPilha() {
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);

        pilha.liberar();

        assertTrue(pilha.estaVazia(), "Após limpar a pilha, o método estaVazia() deve resultar em true");
    }
}