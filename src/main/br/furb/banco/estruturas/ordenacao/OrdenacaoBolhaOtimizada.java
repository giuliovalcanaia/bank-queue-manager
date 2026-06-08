package br.furb.banco.estruturas.ordenacao;

public class OrdenacaoBolhaOtimizada<T extends Comparable<T>> extends OrdenacaoAbstract<T> {
    @Override
    public void ordenar() {
        int i;
        int j;
        int n = getInfo().length;
        boolean trocou;
        int contadorTrocas = 0;
        int contadorCiclos = 0;
        int contadorComparacoes = 0;

        for (i = n - 1; i > 0; i--) {
            contadorCiclos++;
            trocou = false;
            for (j = 0; j < i; j++) {
                contadorComparacoes++;
                if (getInfo()[j].compareTo(getInfo()[j + 1]) > 0) {
                    trocar(j, j + 1);
                    contadorTrocas++;
                    trocou = true;
                }
            }
            // Se não trocou o algoritmo finaliza para otimizar o processo de sorting
            if (!trocou) {
                break;
            }
        }
        System.out.println("--------------- Ordenação Bolha Otimizada ----------------");
        System.out.println("Trocas: " + contadorTrocas);
        System.out.println("Ciclos: " + contadorCiclos);
        System.out.println("Comparações: " + contadorComparacoes);
        System.out.println("----------------------------------------------------------");
    }
}