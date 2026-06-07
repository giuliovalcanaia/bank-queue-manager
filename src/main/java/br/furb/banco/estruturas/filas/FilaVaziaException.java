package src.main.java.br.furb.banco.estruturas.filas;

public class FilaVaziaException extends RuntimeException {
    public FilaVaziaException() {
        super("A fila está vazia");
    }
}
