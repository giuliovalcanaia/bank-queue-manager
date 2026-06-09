package br.furb.banco.estruturas.listas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListaEncadeadaTest {

    private ListaEncadeada<Integer> lista;

    @BeforeEach
    void setUp() {
        // Inicializa uma nova lista antes de cada tsete
        lista = new ListaEncadeada<>();
    }

    @Test
    @DisplayName("Caso 1: Verificar se é reconhecida lista vazia")
    void verificaSeReconheceVazia() {
        assertTrue(lista.estaVazia());
    }

    @Test
    @DisplayName("Caso 2: Verificar se é reconhecida lista não vazia")
    void verificaListaNaoVazia() {
        lista.inserir(5);
        assertFalse(lista.estaVazia());
    }

    @Test
    @DisplayName("Caso 3: Validar inclusão de um número")
    void validaInclusaoDeUmNumero() {
        lista.inserir(5);
        NoLista<Integer> primeiro = lista.getPrimeiro();

        assertEquals(5, primeiro.getInfo());
        assertEquals(null, primeiro.getProximo());
    }


    @Test
    @DisplayName("Caso 4: Validar inclusão de 3 números")
    void validaInclusaoDeTresNumeros() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);

        NoLista<Integer> no1 = lista.getPrimeiro();
        assertEquals(15, no1.getInfo());

        NoLista<Integer> no2 = no1.getProximo();
        assertEquals(10, no2.getInfo());

        NoLista<Integer> no3 = no2.getProximo();
        assertEquals(5, no3.getInfo());

        assertEquals(null, no3.getProximo());
    }


    @Test
    @DisplayName("Caso 5: Validar busca de dados na lista na primeira posição")
    void validarBuscarNaPrimeiraPosicao() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(20, lista.buscar(20).getInfo());
    }

    @Test
    @DisplayName("Caso 6: Validar busca de dados no meio da lista")
    void validaBuscarNoMeioDaLista() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(15, lista.buscar(15).getInfo());
    }

    @Test
    @DisplayName("Caso 7: Validar busca de dado inexistente")
    void validaBuscarDadoInexistente() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(null, lista.buscar(50));
    }

    @Test
    @DisplayName("Caso 8: Validar exclusão de primeiro elemento da lista")
    void validaExclusaoPrimeiroElemento() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.retirar(20);

        assertEquals(15, lista.getPrimeiro().getInfo());
        assertEquals(10, lista.getPrimeiro().getProximo().getInfo());
        assertEquals(5, lista.getPrimeiro().getProximo().getProximo().getInfo());
        // Deve encontrar nó vazio
        assertEquals(null, lista.getPrimeiro().getProximo().getProximo().getProximo());
    }

    @Test
    @DisplayName("Caso 9: Validar exclusão de elemento do meio da lista")
    void validaExclusaoElementoMeio() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.retirar(15);

        assertEquals(20, lista.getPrimeiro().getInfo());
        assertEquals(10, lista.getPrimeiro().getProximo().getInfo());
        assertEquals(5, lista.getPrimeiro().getProximo().getProximo().getInfo());
        assertEquals(null, lista.getPrimeiro().getProximo().getProximo().getProximo());
    }

    @Test
    @DisplayName("Caso 10: Validar que obterNo retorna nó da posição 0")
    void validaObterNoPosicaoZero() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> no = lista.obterNo(0);
        assertEquals(20, lista.obterNo(0).getInfo());
    }

    @Test
    @DisplayName("Caso 11: Validar que obterNo retorna nó da última posição")
    void validaObterNoUltimaPosicao() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> no = lista.obterNo(3);
        assertEquals(5, lista.obterNo(3).getInfo());
    }

    @Test
    @DisplayName("Caso 12: Validar que obterNo recusa tentativa de ler posição inválida")
    void validaObterNoPosicaoInvalida() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        // Expressão lambda
        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.obterNo(10);
        });
    }

    @Test
    @DisplayName("Caso 13: Validar método obterComprimento() para lista vazia")
    void validaObterComprimentoListaVazia() {
        assertEquals(0, lista.obterComprimento());
    }

    @Test
    @DisplayName("Caso 14: Validar método obterComprimento() para lista não vazia")
    void validaObterComprimentoListaNaoVazia() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(4, lista.obterComprimento());
    }
}