package br.furb.banco.modelos;

public class RegistroPorTempo implements Comparable<RegistroPorTempo> {
    RegistroAtendimento registro;

    RegistroPorTempo(RegistroAtendimento registro) {
        this.registro = registro;
    }

    @Override
    public int compareTo(RegistroPorTempo outro) {
        return Long.compare(this.registro.getTempoEsperaMinutos(), outro.registro.getTempoEsperaMinutos());
    }
}
