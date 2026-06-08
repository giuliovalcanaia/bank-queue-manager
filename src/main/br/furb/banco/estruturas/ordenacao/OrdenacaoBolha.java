package br.furb.banco.estruturas.ordenacao;

import br.furb.banco.estruturas.ordenacao.OrdenacaoAbstract;

public class OrdenacaoBolha<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordenar() {
        T[] info = getInfo();

        // Prevenção contra NullPointerException caso o vetor não tenha sido inicializado
        if (info == null) {
            return;
        }

        int n = info.length;

        // Laço externo: controla a quantidade de varreduras no vetor
        for (int i = 0; i < n - 1; i++) {

            // Laço interno: empurra o maior elemento para o final do vetor
            for (int j = 0; j < n - 1 - i; j++) {

                // Se o elemento atual for maior que o próximo, eles trocam de posição.
                // Como T é um objeto, usamos o compareTo(), que retorna um valor > 0 se o primeiro for maior.
                if (info[j].compareTo(info[j + 1]) > 0) {
                    trocar(j, j + 1); // Método implementado na classe abstrata
                }
            }
        }
    }
}