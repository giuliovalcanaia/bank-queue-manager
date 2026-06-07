package java.br.furb.banco.estruturas.pilhas;

public interface Pilha <T> {
  void push (T v);
  T pop ();
  T peek ();
  boolean estaVazia ();
  void liberar ();
}
