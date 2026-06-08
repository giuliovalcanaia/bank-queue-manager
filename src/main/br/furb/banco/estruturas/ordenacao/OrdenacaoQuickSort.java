package br.furb.banco.estruturas.ordenacao;

public class OrdenacaoQuickSort<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordenar() {
        T[] info = getInfo();

        if (info == null || info.length <= 1) {
            return; // Vetor vazio ou com 1 elemento já está ordenado
        }

        // Inicia a recursão englobando todo o vetor (do índice 0 ao último)
        quickSort(0, info.length - 1);
    }

    private void quickSort(int inicio, int fim) {
        if (inicio < fim) {
            // Particiona o vetor e descobre a posição definitiva do pivô
            int posicaoPivo = particionar(inicio, fim);

            // Chama o quickSort recursivamente para a metade à esquerda do pivô
            quickSort(inicio, posicaoPivo - 1);

            // Chama o quickSort recursivamente para a metade à direita do pivô
            quickSort(posicaoPivo + 1, fim);
        }
    }

    private int particionar(int inicio, int fim) {
        T[] info = getInfo();

        // Escolhemos o último elemento como pivô
        T pivo = info[fim];

        // 'i' será o índice do último elemento menor que o pivô encontrado
        int i = inicio - 1;

        // Varre o subvetor do 'inicio' até 'fim - 1'
        for (int j = inicio; j < fim; j++) {

            // Se o elemento atual for menor ou igual ao pivô
            if (info[j].compareTo(pivo) <= 0) {
                i++; // Avança o limite dos menores
                trocar(i, j); // Coloca o elemento menor na porção esquerda
            }
        }

        // Coloca o pivô exatamente na sua posição correta (logo após os menores que ele)
        trocar(i + 1, fim);

        // Retorna a posição final do pivô para que o quickSort saiba onde dividir o vetor nas próximas chamadas
        return i + 1;
    }
}