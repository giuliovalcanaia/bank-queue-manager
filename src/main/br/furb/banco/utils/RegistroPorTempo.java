package br.furb.banco.utils;

import br.furb.banco.modelos.RegistroAtendimento;

public class RegistroPorTempo implements Comparable<RegistroPorTempo> {

    private RegistroAtendimento registro;

    // Construtor
    public RegistroPorTempo(RegistroAtendimento registro) {
        this.registro = registro;
    }

    // Getter and setter
    public RegistroAtendimento getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroAtendimento registro) {
        this.registro = registro;
    }

    /**
     * Compara este registro com outro pelo tempo de espera do cliente na fila,
     * em ordem crescente (menor tempo de espera primeiro).
     * @param outro O outro RegistroPorTempo a ser comparado.
     * @return Valor negativo se este tiver menor tempo de espera, zero se iguais,
     *         ou valor positivo se este tiver maior tempo de espera.
     */
    @Override
    public int compareTo(RegistroPorTempo outro) {
        return Long.compare(this.registro.getTempoEsperaMinutos(), outro.registro.getTempoEsperaMinutos());
    }
}