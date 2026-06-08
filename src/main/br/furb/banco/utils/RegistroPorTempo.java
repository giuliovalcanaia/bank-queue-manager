package br.furb.banco.utils;

import br.furb.banco.modelos.RegistroAtendimento;

/**
 * Classe auxiliar (wrapper) utilizada para ordenação de registros de atendimento
 * pelo critério de tempo de espera do cliente na fila.
 * Implementa Comparable para permitir o uso com algoritmos de ordenação
 * genéricos, como o OrdenacaoQuickSort.
 */
public class RegistroPorTempo implements Comparable<RegistroPorTempo> {

    private RegistroAtendimento registro;

    /**
     * Construtor da classe RegistroPorTempo.
     * @param registro O registro de atendimento a ser encapsulado para ordenação.
     */
    public RegistroPorTempo(RegistroAtendimento registro) {
        this.registro = registro;
    }

    /**
     * Retorna o registro de atendimento encapsulado.
     * @return O objeto RegistroAtendimento associado a este wrapper.
     */
    public RegistroAtendimento getRegistro() {
        return registro;
    }

    /**
     * Define o registro de atendimento encapsulado.
     * @param registro O novo objeto RegistroAtendimento a ser associado.
     */
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