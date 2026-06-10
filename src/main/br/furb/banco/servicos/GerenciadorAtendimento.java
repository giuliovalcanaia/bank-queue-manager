package br.furb.banco.servicos;

import br.furb.banco.estruturas.filas.FilaLista;
import br.furb.banco.estruturas.pilhas.PilhaLista;
import br.furb.banco.modelos.Cliente;
import br.furb.banco.modelos.Guiche;
import br.furb.banco.modelos.RegistroAtendimento;
import br.furb.banco.modelos.TipoAtendimento;

import java.time.LocalTime;

/**
 * Classe responsável por gerenciar as filas de clientes e a lógica de atendimento dos guichês.
 */
public class GerenciadorAtendimento {

    private FilaLista<Cliente> filaPrioridade;
    private FilaLista<Cliente> filaGeral;
    private Guiche[] guiches;
    private PilhaLista<RegistroAtendimento> historicoCompleto;

    /**
     * Construtor do Gerenciador. Inicializa as filas e os guichês.
     * @param qtdGuicheNormal quantidade de guichês para atendimento normal
     * @param qtdGuichePrioridade quantidade de guichês para atendimento prioritário
     */
    public GerenciadorAtendimento(int qtdGuicheNormal, int qtdGuichePrioridade) {
        this.filaPrioridade = new FilaLista<>();
        this.filaGeral = new FilaLista<>();
        this.guiches = new Guiche[qtdGuicheNormal + qtdGuichePrioridade];
        this.historicoCompleto = new PilhaLista<>();

        int indexArray = 0;
        int idContador = 1;

        for (int i = 0; i < qtdGuicheNormal; i++) {
            this.guiches[indexArray] = new Guiche(idContador, TipoAtendimento.GERAL);
            indexArray++;
            idContador++;
        }

        for (int i = 0; i < qtdGuichePrioridade; i++) {
            this.guiches[indexArray] = new Guiche(idContador, TipoAtendimento.PREFERENCIAL);
            indexArray++;
            idContador++;
        }
    }

    /**
     * Insere um cliente na fila correspondente (Prioritária ou Normal).
     * @param cliente Objeto cliente a ser adicionado.
     */
    public void adicionarCliente(Cliente cliente) {
        if (cliente == null) {
            return;
        }

        if (cliente.getTipoAtendimento() == TipoAtendimento.PREFERENCIAL) {
            filaPrioridade.inserir(cliente);
            System.out.println(cliente.getHorarioChegada() + " - Cliente com id " + cliente.getId() + " foi inserido na fila prioritária");
        } else {
            filaGeral.inserir(cliente);
            System.out.println(cliente.getHorarioChegada() + " - Cliente com id " + cliente.getId() + " foi inserido na fila geral");
        }
    }

    /**
     * Executa a regra de chamar o próximo cliente para um guichê específico.
     * @param idGuiche ID do guichê que está disponível para atender (1, 2 ou 3).
     * @param horarioAtual Horário da simulação em que o cliente é chamado.
     * @return O RegistroAtendimento gerado ou null se nenhum cliente foi atendido.
     */
    public RegistroAtendimento chamarProximo(int idGuiche, LocalTime horarioAtual) {
        Guiche guiche = encontrarGuichePorId(idGuiche);

        // Verificação para evitar erros de null
        if (guiche == null) {
            throw new IllegalArgumentException("Guichê com ID " + idGuiche + " não existe.");
        }

        // Fecha o atendimento anterior
        if (guiche.getHistoricoAtendimentos().tamanho() > 0) {
            RegistroAtendimento registroAnterior = guiche.getHistoricoAtendimentos().peek();
            registroAnterior.setHorarioTerminoAtendimento(horarioAtual);
            System.out.println(horarioAtual + " - Guichê " + guiche.getId() + " encerrou o atendimento do cliente " + guiche.getHistoricoAtendimentos().peek().getCliente().getId());
        }

        // Lógica para escolher o próximo a ser chamado
        Cliente clienteEscolhido = null;

        // GUICHÊ PREFERENCIAL: atende EXCULSIVAMENTE a FilaPrioridade
        if (guiche.getTipoAtendimento() == TipoAtendimento.PREFERENCIAL) {
            // Se a fila prioridade não está vazia, chama o próximo prioritário
            if (!filaPrioridade.estaVazia()) {
                clienteEscolhido = filaPrioridade.retirar();
            }
        }

        // GUICHÊ GERAL: seguem a lógica de alternância equilibrada
        else if (guiche.getTipoAtendimento() == TipoAtendimento.GERAL) {
            // Se os guichês gerais já chamaram um prioritário
            if (guiche.getHistoricoAtendimentos().estaVazia()) {
                if (!filaPrioridade.estaVazia()) {
                    clienteEscolhido = filaPrioridade.retirar();
                } else if (!filaGeral.estaVazia()) {
                    clienteEscolhido = filaGeral.retirar();
                }
        } else {
                if (guiche.getHistoricoAtendimentos().peek().getTipoAtendimento() == TipoAtendimento.PREFERENCIAL) {
                    if (!filaGeral.estaVazia()) {
                        clienteEscolhido = filaGeral.retirar();
                    } else if (!filaPrioridade.estaVazia()) {
                        clienteEscolhido = filaPrioridade.retirar();
                    }
                } else {
                    if (!filaPrioridade.estaVazia()) {
                        clienteEscolhido = filaPrioridade.retirar();
                    } else if (!filaGeral.estaVazia()) {
                        clienteEscolhido = filaGeral.retirar();
                    }
                }
            }
    }

        // Se um cliente foi selecionado pelas regras acima, cria o registro e salva na pilha do guichê
        if (clienteEscolhido != null) {
//            Random tempoAtendimento = new Random();
            RegistroAtendimento registro = new RegistroAtendimento(clienteEscolhido, clienteEscolhido.getTipoAtendimento(), horarioAtual, guiche);
            guiche.registrarAtendimento(registro);
            historicoCompleto.push(registro);
            System.out.println(horarioAtual + " - Guichê " + guiche.getId() + " chamou, e o cliente escolhido foi " + registro.getCliente().getId());
            return registro;
        }
        // Último caso possível: ambas as filas estavam vazias
        System.out.println(horarioAtual + " - Guichê " + guiche.getId() + " chamou mas a fila estava vazia");
        return null;
    }

    /**
     * Método auxiliar para localizar um guichê no meio de um vetor, pelo ID estruturado no construtor.
     */
    private Guiche encontrarGuichePorId(int id) {
        for (Guiche g : guiches) {
            if (g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    // Getters

    public Guiche[] getGuiches() {
        return guiches;
    }

    public FilaLista<Cliente> getFilaPrioridade() {
        return filaPrioridade;
    }

    public FilaLista<Cliente> getFilaGeral() {
        return filaGeral;
    }

    public PilhaLista<RegistroAtendimento> getHistoricoCompleto() {
        return historicoCompleto;
    }
}