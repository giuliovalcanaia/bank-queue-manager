package br.furb.banco.estruturas.ordenacao;

public class OrdenacaoQuickSort<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordenar() {
        int n = getInfo().length - 1;
        quickSort(0,n);
    }

    private void quickSort(int inicio, int fim) {
        if (inicio < fim) {
            int idxPivo = particionar(inicio, fim);
            // Chama o lado esquerdo
            quickSort(inicio, idxPivo - 1);
            // Chama o lado direito
            quickSort(idxPivo+1, fim);
        }
    }

    private int particionar(int inicio, int fim) {
        int a = inicio;
        int b = fim + 1;
        T pivo = getInfo()[inicio];

        while (true) {
            do {
                a = a + 1;
            } while (a <= fim && getInfo()[a].compareTo(pivo) < 0);

            do {
                b = b - 1;
            } while (b >= inicio && getInfo()[b].compareTo(pivo) > 0);

            if (a >= b) {
                break;
            }
            trocar(a, b);
        }

        trocar(b, inicio);
        return b;
    }
}