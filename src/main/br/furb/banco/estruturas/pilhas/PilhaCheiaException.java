package java.br.furb.banco.estruturas.pilhas;

public class PilhaCheiaException extends RuntimeException{
  public PilhaCheiaException() {
    super("A pilha está cheia");
  }
}
