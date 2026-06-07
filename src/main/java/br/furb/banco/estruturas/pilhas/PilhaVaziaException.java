package src.main.java.br.furb.banco.estruturas.pilhas;

public class PilhaVaziaException extends RuntimeException{
  public PilhaVaziaException() {
    super("A pilha está vazia");
  }
}
