package br.furb.banco.estruturas.ordenacao;

public abstract class OrdenacaoAbstract <T extends Comparable<T>> {
    private T[] info;
    protected double tempoExecucao = 0.0;

    public T[] getInfo() {
        return info;
    }

    // setInfo precisa obrigatoriamente passar um vetor
    public void setInfo(T[] info) {
        this.info = info;
    }

    public void trocar(int a, int b) {
        T temp = getInfo()[a];
        getInfo()[a] = getInfo()[b];
        getInfo()[b] = temp;
    }

    public abstract void ordenar();

    public void ordenarComCronometro() {
        long tempoInicio = System.nanoTime();

        ordenar();

        long tempoFim = System.nanoTime();
        this.tempoExecucao = (tempoFim - tempoInicio) / 1_000_000.0;

        System.out.println("Tempo de execução: " + this.tempoExecucao + " ms");
        System.out.println("----------------------------------------------------------\n");
    }
}
