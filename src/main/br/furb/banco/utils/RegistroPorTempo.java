package br.furb.banco.utils;

import br.furb.banco.modelos.RegistroAtendimento;

public class RegistroPorTempo implements Comparable<RegistroPorTempo> {
    private RegistroAtendimento registro;

    public RegistroPorTempo(RegistroAtendimento registro) {
        this.registro = registro;
    }

    public RegistroAtendimento getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroAtendimento registro) {
        this.registro = registro;
    }

    @Override
    public int compareTo(RegistroPorTempo outro) {
        return Long.compare(this.registro.getTempoEsperaMinutos(), outro.registro.getTempoEsperaMinutos());
    }
}
