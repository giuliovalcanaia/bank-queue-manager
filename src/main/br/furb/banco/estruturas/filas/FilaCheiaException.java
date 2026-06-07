package br.furb.banco.estruturas.filas;

public class FilaCheiaException extends RuntimeException {
    public FilaCheiaException() {
        super("A fila está cheia.");
    }
}
