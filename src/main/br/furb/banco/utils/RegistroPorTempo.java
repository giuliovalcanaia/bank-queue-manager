package br.furb.banco.utils;

import br.furb.banco.modelos.RegistroAtendimento;

public class RegistroPorTempo implements Comparable<RegistroPorTempo> {
    private RegistroAtendimento registro;

    // Construtor que recebe o objeto já existente
    public RegistroPorTempo(RegistroAtendimento registro) {
        this.registro = registro;
    }

    public RegistroAtendimento getRegistro() {
        return registro;
    }

    @Override
    public int compareTo(RegistroPorTempo outro) {
        // Ordenação crescente por tempo de espera
        return Long.compare(this.registro.getTempoEspera(), outro.registro.getTempoEspera());
    }
}