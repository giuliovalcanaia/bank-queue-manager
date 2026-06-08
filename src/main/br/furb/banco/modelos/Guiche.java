package br.furb.banco.modelos;

import br.furb.banco.estruturas.pilhas.*;

/**
 * Classe que representa um Guichê no sistema de atendimento.
 */
public class Guiche {

    private int id;
    private TipoGuiche tipo;
    private PilhaLista<RegistroAtendimento> historicoAtendimentos;
    private boolean ultimoFoiPrioridade;

    /**
     * Construtor do Guichê.
     * * @param id   Identificador do guichê (ex: 1, 2, 3)
     * @param tipo Tipo do guichê (ex: TipoGuiche.PREFERENCIAL, TipoGuiche.GERAL)
     */
    public Guiche(int id, TipoGuiche tipo) {
        this.id = id;
        this.tipo = tipo;
        this.historicoAtendimentos = new PilhaLista<>();
        this.ultimoFoiPrioridade = false;
    }

    /**
     * Registra o atendimento empilhando-o no histórico e atualizando a flag de alternância.
     * * @param registro O registro do atendimento recém-finalizado.
     */
    public void registrarAtendimento(RegistroAtendimento registro) {
        // Empilha o registro na estrutura
        this.historicoAtendimentos.push(registro);

        // Atualiza a flag de prioridade
        this.ultimoFoiPrioridade = registro.getCliente().isPrioritario();
    }

    // Getters

    public int getId() {
        return id;
    }

    public TipoGuiche getTipo() {
        return tipo;
    }

    public PilhaLista<RegistroAtendimento> getHistoricoAtendimentos() {
        return historicoAtendimentos;
    }

    public boolean isUltimoFoiPrioridade() {
        return ultimoFoiPrioridade;
    }

    @Override
    public String toString() {
        return "Guiche { " +
                "ID = " + id +
                ", Tipo = " + tipo +
                ", Histórico (Tamanho) = " + historicoAtendimentos.tamanho() + // Assumindo que sua pilha tem esse método
                " }";
    }
}