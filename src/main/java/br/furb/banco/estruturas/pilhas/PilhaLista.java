package src.main.java.br.furb.banco.estruturas.pilhas;

import src.main.java.br.furb.banco.estruturas.listas.*;

public class PilhaLista <T> implements Pilha <T>{
  private ListaEncadeada<T> lista;

  public PilhaLista() {
    this.lista = new ListaEncadeada<>();
  }

  @Override
  public void push(T info) {
    lista.inserir(info);
  }

  @Override
  public T pop() {
    if (this.estaVazia()) {
      throw new PilhaVaziaException();
    }

    T valor = lista.getPrimeiro().getInfo();

    lista.retirar(valor);

    return valor;
  }

  @Override
  public T peek() {
    if (lista.estaVazia()){
      throw new PilhaVaziaException();
    }
    return lista.getPrimeiro().getInfo();
  }

  @Override
  public boolean estaVazia() {
    return lista.estaVazia();
  }

  @Override
  public void liberar() {
    this.lista = new ListaEncadeada<>();
  }

  public String toString(){
    return lista.toString();
  }

}
