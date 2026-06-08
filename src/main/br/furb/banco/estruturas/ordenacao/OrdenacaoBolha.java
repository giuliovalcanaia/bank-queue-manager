package br.furb.banco.estruturas.ordenacao;

public class OrdenacaoBolha<T extends Comparable<T>> extends OrdenacaoAbstract<T>{

    @Override
    public void ordenar() {
        int i;
        int j;
        int n = getInfo().length;
        int contadorTrocas = 0;
        int contadorCiclos = 0;
        int contadorComparacoes = 0;

        for (i = n - 1; i > 0; i--) {
            contadorCiclos++;
            for (j = 0; j < i; j++) {
                contadorComparacoes++;
                // Compara os valores nas posições j e o seu sucessor. Caso seja verdadeiro,
                // troca j com o seu sucessor.
                if (getInfo()[j].compareTo(getInfo()[j + 1]) > 0) {
                    trocar(j, (j + 1));
                    contadorTrocas++;
                }
            }
        }
        System.out.println("------------------- Ordenação Bolha ---------------------");
        System.out.println("Trocas: " + contadorTrocas);
        System.out.println("Ciclos: " + contadorCiclos);
        System.out.println("Comparações: " + contadorComparacoes);
        System.out.println("----------------------------------------------------------");
    }
}
