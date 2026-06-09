package br.furb.banco.estruturas.ordenacao;

public class OrdenacaoBolha<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordenar() {
        T[] info = getInfo();

        int n = info.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {

                // Se o elemento atual for maior que o próximo, eles trocam de posição.
                // Como T é um objeto, usamos o compareTo(), que retorna um valor > 0 se o primeiro for maior.
                if (info[j].compareTo(info[j + 1]) > 0) {
                    trocar(j, j + 1);
                }
            }
        }
    }
}