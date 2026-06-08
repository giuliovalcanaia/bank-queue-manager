package br.furb.banco.estruturas.listas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListaEncadeadaTest {

    private ListaEncadeada<Integer> lista;

    @BeforeEach
    public void setUp() {
        lista = new ListaEncadeada<>();
    }

    @Test
    @DisplayName("Caso 1: Verificar se é reconhecida lista vazia")
    public void testCaso01ListaVazia() {
        assertTrue(lista.estaVazia(), "A lista recém-construída deve estar vazia.");
    }

    @Test
    @DisplayName("Caso 2: Verificar se é reconhecida lista não vazia")
    public void testCaso02ListaNaoVazia() {
        lista.inserir(5);
        assertFalse(lista.estaVazia(), "A lista não deve estar vazia após adicionar um elemento.");
    }

    @Test
    @DisplayName("Caso 3: Validar inclusão de um número")
    public void testCaso03InclusaoDeUmNumero() {
        lista.inserir(5);
        NoLista<Integer> primeiro = lista.getPrimeiro();

        assertNotNull(primeiro, "Deve retornar um nó válido.");
        assertEquals(5, primeiro.getInfo(), "O nó deve conter o valor 5.");
        assertNull(primeiro.getProximo(), "Não deve haver mais nós na lista.");
    }


    @Test
    @DisplayName("Caso 4: Validar inclusão de 3 números")
    public void testCaso04InclusaoDeTresNumeros() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);

        NoLista<Integer> no1 = lista.getPrimeiro();
        assertNotNull(no1);
        assertEquals(15, no1.getInfo(), "O primeiro valor deve ser 15.");

        NoLista<Integer> no2 = no1.getProximo();
        assertNotNull(no2);
        assertEquals(10, no2.getInfo(), "O segundo valor deve ser 10.");

        NoLista<Integer> no3 = no2.getProximo();
        assertNotNull(no3);
        assertEquals(5, no3.getInfo(), "O terceiro valor deve ser 5.");

        assertNull(no3.getProximo(), "Deve haver apenas 3 nós.");
    }


    @Test
    @DisplayName("Caso 5: Validar busca de dados na lista na primeira posição")
    public void testCaso05BuscarNaPrimeiraPosicao() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> no = lista.buscar(20);
        assertNotNull(no, "O nó com o valor 20 deve ser encontrado.");
        assertEquals(20, no.getInfo());
    }

    @Test
    @DisplayName("Caso 6: Validar busca de dados no meio da lista")
    public void testCaso06BuscarNoMeioDaLista() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> no = lista.buscar(15);
        assertNotNull(no, "O nó com o valor 15 deve ser encontrado.");
        assertEquals(15, no.getInfo());
    }

    @Test
    @DisplayName("Caso 7: Validar busca de dado inexistente")
    public void testCaso07BuscarDadoInexistente() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> no = lista.buscar(50);
        assertNull(no, "A busca por um dado inexistente deve retornar null.");
    }

    @Test
    @DisplayName("Caso 8: Validar exclusão de primeiro elemento da lista")
    public void testCaso08ExclusaoPrimeiroElemento() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.retirar(20);

        assertEquals(15, lista.getPrimeiro().getInfo());
        assertEquals(10, lista.getPrimeiro().getProximo().getInfo());
        assertEquals(5, lista.getPrimeiro().getProximo().getProximo().getInfo());
        assertNull(lista.getPrimeiro().getProximo().getProximo().getProximo(), "A lista deve conter apenas 15, 10 e 5.");
    }

    @Test
    @DisplayName("Caso 9: Validar exclusão de elemento do meio da lista")
    public void testCaso09ExclusaoElementoMeio() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.retirar(15);

        assertEquals(20, lista.getPrimeiro().getInfo());
        assertEquals(10, lista.getPrimeiro().getProximo().getInfo());
        assertEquals(5, lista.getPrimeiro().getProximo().getProximo().getInfo());
        assertNull(lista.getPrimeiro().getProximo().getProximo().getProximo(), "A lista deve conter apenas 20, 10 e 5.");
    }

    @Test
    @DisplayName("Caso 10: Validar que obterNo() retorna nó da posição 0")
    public void testCaso10ObterNoPosicaoZero() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> no = lista.obterNo(0);
        assertNotNull(no);
        assertEquals(20, no.getInfo(), "O nó da posição 0 deve armazenar 20.");
    }

    @Test
    @DisplayName("Caso 11: Validar que obterNo() retorna nó da última posição")
    public void testCaso11ObterNoUltimaPosicao() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> no = lista.obterNo(3);
        assertNotNull(no);
        assertEquals(5, no.getInfo(), "O nó da posição 3 deve armazenar 5.");
    }

    @Test
    @DisplayName("Caso 12: Validar que obterNo() recusa tentativa de ler posição inválida")
    public void testCaso12ObterNoPosicaoInvalida() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.obterNo(10);
        }, "Acesso a posição inválida deve lançar IndexOutOfBoundsException.");
    }

    @Test
    @DisplayName("Caso 13: Validar método obterComprimento() para lista vazia")
    public void testCaso13ObterComprimentoListaVazia() {
        assertEquals(0, lista.obterComprimento(), "O comprimento de uma lista vazia deve ser 0.");
    }

    @Test
    @DisplayName("Caso 14: Validar método obterComprimento() para lista não vazia")
    public void testCaso14ObterComprimentoListaNaoVazia() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(4, lista.obterComprimento(), "O comprimento da lista deve ser 4.");
    }
}