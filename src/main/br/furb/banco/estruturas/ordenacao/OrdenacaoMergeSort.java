package br.furb.banco.estruturas.ordenacao;

public class OrdenacaoMergeSort<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordenar() {
        T[] info = getInfo();

        if (info == null || info.length <= 1) {
            return; // Vetor vazio ou com 1 elemento já está ordenado
        }

        // Chama o método recursivo passando o primeiro e o último índice
        mergeSort(0, info.length - 1);
    }

    private void mergeSort(int inicio, int fim) {
        // Condição de parada da recursão: quando inicio >= fim
        if (inicio < fim) {
            int meio = (inicio + fim) / 2; // Encontra o meio para dividir o vetor

            mergeSort(inicio, meio);       // Ordena recursivamente a primeira metade
            mergeSort(meio + 1, fim);      // Ordena recursivamente a segunda metade

            merge(inicio, fim, meio);      // Intercala as duas metades já ordenadas
        }
    }

    @SuppressWarnings("unchecked")
    private void merge(int inicio, int fim, int meio) {
        T[] info = getInfo();

        // Em Java, não podemos instanciar arrays de tipos genéricos diretamente (new T[]).
        // A solução de contorno segura neste caso é criar um array de Comparable e fazer o cast.
        T[] aux = (T[]) new Comparable[fim - inicio + 1];

        int i = inicio;      // Aponta para o início da primeira metade
        int j = meio + 1;    // Aponta para o início da segunda metade
        int k = 0;           // Aponta para o índice do vetor auxiliar

        // Compara os elementos das duas metades e insere o menor no vetor auxiliar
        while (i <= meio && j <= fim) {
            // Se o elemento da esquerda for menor ou igual ao da direita
            if (info[i].compareTo(info[j]) <= 0) {
                aux[k] = info[i];
                i++;
            } else {
                aux[k] = info[j];
                j++;
            }
            k++;
        }

        // Copia os elementos que sobraram da primeira metade (se houver)
        while (i <= meio) {
            aux[k] = info[i];
            i++;
            k++;
        }

        // Copia os elementos que sobraram da segunda metade (se houver)
        while (j <= fim) {
            aux[k] = info[j];
            j++;
            k++;
        }

        // Transfere todos os elementos do vetor auxiliar ordenado de volta para o vetor original
        for (i = inicio, k = 0; i <= fim; i++, k++) {
            info[i] = aux[k];
        }
    }
}