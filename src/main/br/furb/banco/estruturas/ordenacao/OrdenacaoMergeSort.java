package br.furb.banco.estruturas.ordenacao;

public class OrdenacaoMergeSort<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordenar() {
        T[] info = getInfo();

        mergeSort(0, info.length - 1);
    }

    private void mergeSort(int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;

            mergeSort(inicio, meio);
            mergeSort(meio + 1, fim);

            merge(inicio, fim, meio);
        }
    }

    // Foi necessário adicionar para o compilador não reclamar
    @SuppressWarnings("unchecked")
    private void merge(int inicio, int fim, int meio) {

        // Lado esquerdo
        int tamEsquerda = meio - inicio + 1;
        T[] esquerda = (T[]) new Comparable[tamEsquerda];
        for (int i = 0; i < tamEsquerda; i++) {
            esquerda[i] = getInfo()[inicio + i];
        }

        // Lado direito
        int tamDireita = fim - meio;
        T[] direita = (T[]) new Comparable[tamDireita];
        for (int j = 0; j < tamDireita; j++) {
            direita[j] = getInfo()[meio + 1 + j];
        }

        int cEsq = 0;
        int cDir = 0;
        int i;

        for (i = inicio; i <= fim; i++) {
            if (cEsq < tamEsquerda && cDir < tamDireita) {

                if (esquerda[cEsq].compareTo(direita[cDir]) <= 0) {
                    getInfo()[i] = esquerda[cEsq];
                    cEsq++;
                } else {
                    getInfo()[i] = direita[cDir];
                    cDir++;
                }

            } else {
                break;
            }
        }

        while (cEsq < tamEsquerda) {
            getInfo()[i] = esquerda[cEsq];
            cEsq++;
            i++;
        }

        while (cDir < tamDireita) {
            getInfo()[i] = direita[cDir];
            cDir++;
            i++;
        }
    }

}