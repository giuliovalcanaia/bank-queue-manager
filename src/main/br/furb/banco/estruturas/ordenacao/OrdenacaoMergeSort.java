package br.furb.banco.estruturas.ordenacao;

public class OrdenacaoMergeSort<T extends Comparable<T>> extends OrdenacaoAbstract<T> {
    int contadorTrocas = 0;
    int contadorCiclos = 0;
    int contadorComparacoes = 0;
    double tempoExecucao = 0.0;

    @Override
    public void ordenar() {
        int n = getInfo().length - 1;
        mergeSort(0, n);

        System.out.println("-------------------- Merge Sort --------------------------");
        System.out.println("Trocas (Movimentações): " + contadorTrocas);
        System.out.println("Ciclos: " + contadorCiclos);
        System.out.println("Comparações: " + contadorComparacoes);
        System.out.println("----------------------------------------------------------");
    }

    private void mergeSort(int inicio, int fim) {
        if(inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(inicio, meio);
            mergeSort(meio + 1, fim);
            merge(inicio, fim, meio);
        }
    }

    @SuppressWarnings("unchecked")
    private void merge(int inicio, int fim, int meio) {
        contadorCiclos++;

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

                contadorComparacoes++;

                if (esquerda[cEsq].compareTo(direita[cDir]) <= 0) {
                    getInfo()[i] = esquerda[cEsq];
                    cEsq++;
                } else {
                    getInfo()[i] = direita[cDir];
                    cDir++;
                }

                contadorTrocas++;

            } else {
                break;
            }
        }

        while (cEsq < tamEsquerda) {
            getInfo()[i] = esquerda[cEsq];
            cEsq++;
            i++;
            contadorTrocas++;
        }

        while (cDir < tamDireita) {
            getInfo()[i] = direita[cDir];
            cDir++;
            i++;
            contadorTrocas++;
        }
    }
}