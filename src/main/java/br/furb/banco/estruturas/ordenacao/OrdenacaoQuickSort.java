package AlgoritmosEEstruturaDeDados.Ordenacao.BubbleQuickMerge;

public class OrdenacaoQuickSort<T extends Comparable<T>> extends OrdenacaoAbstract<T> {
    int contadorTrocas = 0;
    int contadorCiclos = 0;
    int contadorComparacoes = 0;

    @Override
    public void ordenar() {
        int n = getInfo().length - 1;
        quickSort(0,n);

        System.out.println("-------------------- Quick Sort --------------------------");
        System.out.println("Trocas: " + contadorTrocas);
        System.out.println("Ciclos: " + contadorCiclos);
        System.out.println("Comparações: " + contadorComparacoes);
        System.out.println("----------------------------------------------------------");
    }

    private void quickSort(int inicio, int fim) {
        if (inicio < fim) {
            int idxPivo = particionar(inicio, fim);
            quickSort(inicio, idxPivo - 1);
            quickSort(idxPivo+1, fim);
        }
    }

    private int particionar(int inicio, int fim) {
        int a = inicio;
        int b = fim + 1;
        T pivo = getInfo()[inicio];

        while (true) {
            contadorCiclos++;
            do {
                a = a + 1;
                contadorComparacoes++;
            } while (a <= fim && getInfo()[a].compareTo(pivo) < 0);

            do {
                b = b - 1;
                contadorComparacoes++;
            } while (b >= inicio && getInfo()[b].compareTo(pivo) > 0);

            if (a >= b) {
                break;
            }

            trocar(a, b);
            contadorTrocas++;
        }

        trocar(b, inicio);
        contadorTrocas++;
        return b;
    }
}
