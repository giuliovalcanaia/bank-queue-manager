package br.furb.banco.estruturas.ordenacao;

public abstract class OrdenacaoAbstract <T extends Comparable<T>> {
    private T[] info;

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
}
