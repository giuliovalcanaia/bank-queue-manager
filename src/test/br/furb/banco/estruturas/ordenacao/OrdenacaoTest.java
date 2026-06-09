package br.furb.banco.estruturas.ordenacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrdenacaoTest {

    private Integer[] vetorEntrada;
    private Integer[] vetorEsperado;

    @BeforeEach
    void setUp() {
        vetorEntrada = new Integer[]{70, 2, 88, 15, 90, 30};
        vetorEsperado = new Integer[]{2, 15, 30, 70, 88, 90};
    }

    @Test
    @DisplayName("Caso 1: Validar algoritmo de ordenação Bolha")
    void testeValidarOrdenacaoBolha() {
        OrdenacaoAbstract<Integer> ordenacao = new OrdenacaoBolha<>();
        ordenacao.setInfo(vetorEntrada);

        ordenacao.ordenar();

        assertArrayEquals(vetorEsperado, ordenacao.getInfo());
    }

    @Test
    @DisplayName("Caso 2: Validar algoritmo de ordenação bolha otimizado")
    void testeValidarOrdenacaoBolhaOtimizada() {
        OrdenacaoAbstract<Integer> ordenacao = new OrdenacaoBolhaOtimizada<>();
        ordenacao.setInfo(vetorEntrada);

        ordenacao.ordenar();

        assertArrayEquals(vetorEsperado, ordenacao.getInfo());
    }

    @Test
    @DisplayName("Caso 3: Validar algoritmo de ordenação Quicksort")
    void testeValidarOrdenacaoQuickSort() {
        OrdenacaoAbstract<Integer> ordenacao = new OrdenacaoQuickSort<>();
        ordenacao.setInfo(vetorEntrada);

        ordenacao.ordenar();

        assertArrayEquals(vetorEsperado, ordenacao.getInfo());
    }

    @Test
    @DisplayName("Caso 4: Validar algoritmo de ordenação MergeSort")
    void testeValidarOrdenacaoMergeSort() {
        OrdenacaoAbstract<Integer> ordenacao = new OrdenacaoMergeSort<>();
        ordenacao.setInfo(vetorEntrada);

        ordenacao.ordenar();

        assertArrayEquals(vetorEsperado, ordenacao.getInfo());
    }
}