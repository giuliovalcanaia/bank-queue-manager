package br.furb.banco.estruturas.ordenacao;

public class OrdenacaoBolhaOtimizada<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordenar() {
        T[] info = getInfo();

        if (info == null) {
            return;
        }

        int n = info.length;
        boolean trocou; // Variável para rastrear se houve alguma troca na iteração

        for (int i = 0; i < n - 1; i++) {
            trocou = false; // Reseta a flag no início de cada nova varredura

            for (int j = 0; j < n - 1 - i; j++) {

                if (info[j].compareTo(info[j + 1]) > 0) {
                    trocar(j, j + 1);
                    trocou = true; // Marca que pelo menos uma troca aconteceu
                }
            }

            // Se o laço interno rodou inteiro e não fez nenhuma troca, o vetor já está ordenado.
            // O comando 'break' interrompe o laço externo precocemente.
            if (!trocou) {
                break;
            }
        }
    }
}