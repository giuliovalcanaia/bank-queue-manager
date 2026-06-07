package java.br.furb.banco.estruturas.filas;
import java.br.furb.banco.estruturas.listas.*;

import java.br.furb.banco.estruturas.listas.ListaEncadeada;

public class FilaLista<T> implements Fila<T> {
  private ListaEncadeada<T> lista = new ListaEncadeada<>();

  @Override
  public void inserir(T valor) {
    this.lista.inserirNoFinal(valor);
  }

  @Override
  public boolean estaVazia(){
    return this.lista.estaVazia();
  }

  @Override
  public T peek() {
    if (this.estaVazia()) {
      throw new FilaVaziaException();
    }
    return this.lista.getPrimeiro().getInfo();
  }

  @Override
  public T retirar() {
    if (this.estaVazia()) {
      throw new FilaVaziaException();
    }
    T valor = this.lista.getPrimeiro().getInfo();
    this.lista.retirar(valor);
    return valor;
  }

  @Override
  public void liberar(){
    this.lista = new ListaEncadeada<>();
  }

  public String toString() {
    String resultado = "";
    NoLista<T> ponteiro = this.lista.getPrimeiro();
    while (ponteiro != null) {
      if(ponteiro.getProximo() != null){
        resultado = resultado + ",";
      }
      resultado = resultado + String.valueOf(ponteiro.getInfo());
      ponteiro = ponteiro.getProximo();
    }
    return resultado;
  }
}
