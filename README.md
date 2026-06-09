# Sistema gerenciador de fila de atendimento bancário 
## Diagrama de classes simplificado
Contém as classes do projeto com exceção das classes de estruturas de dados
```mermaid
classDiagram

    %% MODELOS

    class TipoAtendimento {
        <<enumeration>>
        PREFERENCIAL
        GERAL
        +getDescricao() String
    }

    class Cliente {
        -id: int
        -tipoAtendimento: TipoAtendimento 
        -horarioChegada: LocalTime
        +toString() String
    }

    class RegistroAtendimento {
        -cliente: Cliente
        -horarioInicioAtendimento: LocalTime
        -tempoAtendimento: int
        +getTempEsperaMinutos() long
        +toString() String
    }

    class Guiche {
        -id: int
        -tipoAtendimento: TipoAtendimento
        -historicoAtendimentos: PilhaLista~RegistroAtendimento~
        +registrarAtendimento(registro: RegistroAtendimento) void
        +toString() String
    }

    %% UTILS

    class RegistroPorTempo {
        -registro: RegistroAtendimento
        +compareTo(outro: RegistroPorTempo) int
    }

    class RegistroPorHorario {
        +registro: RegistroAtendimento
        +compareTo(outro: RegistroPorHorario) int
    }

    %% SERVIÇOS

    class GerenciadorAtendimento {
        -filaPrioridade: FilaLista~Cliente~
        -filaNormal: FilaLista~Cliente~
        -guiches: Guiche[]
        -consecutivosPrioritariosGerais: int
        +adicionarCliente(cliente: Cliente) void
        +chamarProximo(idGuiche: int, horarioAtual: LocalTime) RegistroAtendimento
        -encontrarGuichePorId(id: int) Guiche
    }

    class Relatorio {
        +imprimirRelatorio(gerenciador: GerenciadorAtendimento) void
        -imprimirOrdenacoes(registros: RegistroAtendimento[]) void
    }

    %% RELAÇÕES

    %% Comparable
    RegistroPorTempo     ..|>  Comparable
    RegistroPorHorario   ..|>  Comparable

    %% Modelos
    %% *-- = composição, ou seja, as classes só fazem sentido juntas
    
    RegistroAtendimento  "1"  *--  "1"  Cliente
    Guiche               "1"  *--  "1"  TipoAtendimento
    Guiche               "1"  *--  "1"  PilhaLista~RegistroAtendimento~
    Cliente              "1"  *--  "1"  TipoAtendimento

    %% Utils
    RegistroPorTempo     "1"  *--  "1"  RegistroAtendimento
    RegistroPorHorario   "1"  *--  "1"  RegistroAtendimento

    %% Serviços
    GerenciadorAtendimento  "1"  *--  "1..*"  Guiche
    GerenciadorAtendimento  "1"  *--  "1"     FilaLista~Cliente~
    Relatorio-->              GerenciadorAtendimento
    Relatorio-->              OrdenacaoQuickSort~T~
    Relatorio-->              RegistroPorTempo
    Relatorio-->              RegistroPorHorario
```

## Diagrama de classes completo
Contém o diagrama de todas as classes, inclusive relativo às classes das estruturas de dados.
```mermaid
classDiagram

    %% INTERFACES

    class Pilha~T~ {
        <<interface>>
        +push(v: T) void
        +pop() T
        +peek() T
        +estaVazia() boolean
        +liberar() void
    }

    class Fila~T~ {
        <<interface>>
        +inserir(valor: T) void
        +estaVazia() boolean
        +peek() T
        +retirar() T
        +liberar() void
    }

    %% ESTRUTURAS

    class NoLista~T~ {
        -info: T
        -proximo: NoLista~T~
        +getInfo() T
        +setInfo(info: T) void
        +getProximo() NoLista~T~
        +setProximo(proximo: NoLista~T~) void
    }

    class ListaEncadeada~T~ {
        -primeiro: NoLista~T~
        -ultimo: NoLista~T~
        +ListaEncadeada()
        +getPrimeiro() NoLista~T~
        +getUltimo() NoLista~T~
        +inserir(valor: T) void
        +inserirNoFinal(valor: T) void
        +estaVazia() Boolean
        +buscar(valor: T) NoLista~T~
        +retirar(valor: T) void
        +obterComprimento() int
        +obterNo(idx: int) NoLista~T~
        +toString() String
    }

    class PilhaLista~T~ {
        -lista: ListaEncadeada~T~
        +PilhaLista()
        +push(info: T) void
        +pop() T
        +peek() T
        +estaVazia() boolean
        +liberar() void
        +tamanho() int
        +toString() String
    }

    class FilaLista~T~ {
        -lista: ListaEncadeada~T~
        +inserir(valor: T) void
        +estaVazia() boolean
        +peek() T
        +retirar() T
        +liberar() void
        +toString() String
    }

    class PilhaVaziaException {
        +PilhaVaziaException()
    }

    class PilhaCheiaException {
        +PilhaCheiaException()
    }

    class FilaVaziaException {
        +FilaVaziaException()
    }

    class FilaCheiaException {
        +FilaCheiaException()
    }

    %% ORDENAÇÃO

    class OrdenacaoAbstract~T extends Comparable~ {
        <<abstract>>
        -info: T[]
        +getInfo() T[]
        +setInfo(info: T[]) void
        +trocar(a: int, b: int) void
        +ordenar()* void
    }

    class OrdenacaoQuickSort~T extends Comparable~ {
        +ordenar() void
        -quickSort(inicio: int, fim: int) void
        -particionar(inicio: int, fim: int) int
    }

    %% MODELOS

    class TipoAtendimento {
        <<enumeration>>
        PREFERENCIAL
        GERAL
        -descricao: String
        +getDescricao() String
    }

    class Cliente {
        -id: int
        -tipoAtendimento: TipoAtendimento
        -horarioChegada: LocalTime
        +Cliente(id: int, tipoAtendimento: TipoAtendimento, horarioChegada: LocalTime)
        +getId() int
        +getTipoAtendimento() TipoAtendimento
        +getHorarioChegada() LocalTime
        +setId(id: int) void
        +setTipoAtendimento(tipoAtendimento: TipoAtendimento) void
        +setHorarioChegada(horarioChegada: LocalTime) void
        +toString() String
    }

    class RegistroAtendimento {
        -cliente: Cliente
        -horarioInicioAtendimento: LocalTime
        -tempoAtendimento: int
        +RegistroAtendimento(cliente: Cliente, horarioInicioAtendimento: LocalTime, tempoAtendimento: int)
        +getTempoEsperaMinutos() long
        +getCliente() Cliente
        +getHorarioInicioAtendimento() LocalTime
        +getTempoAtendimento() int
        +toString() String
    }

    class Guiche {
        -id: int
        -tipoAtendimento: TipoAtendimento
        -historicoAtendimentos: PilhaLista~RegistroAtendimento~
        +Guiche(id: int, tipo: TipoAtendimento)
        +registrarAtendimento(registro: RegistroAtendimento) void
        +getId() int
        +getTipoAtendimento() TipoAtendimento
        +getHistoricoAtendimentos() PilhaLista~RegistroAtendimento~
        +toString() String
    }

    %% UTILS

    class RegistroPorTempo {
        -registro: RegistroAtendimento
        +RegistroPorTempo(registro: RegistroAtendimento)
        +getRegistro() RegistroAtendimento
        +setRegistro(registro: RegistroAtendimento) void
        +compareTo(outro: RegistroPorTempo) int
    }

    class RegistroPorHorario {
        -registro: RegistroAtendimento
        +RegistroPorHorario(registro: RegistroAtendimento)
        +getRegistro() RegistroAtendimento
        +setRegistro(registro: RegistroAtendimento) void
        +compareTo(outro: RegistroPorHorario) int
    }

    %% SERVIÇOS

    class GerenciadorAtendimento {
        -filaPrioridade: FilaLista~Cliente~
        -filaGeral: FilaLista~Cliente~
        -guiches: Guiche[]
        -consecutivosPrioritariosGerais: int
        +GerenciadorAtendimento(qtdGuicheNormal: int, qtdGuichePrioridade: int)
        +adicionarCliente(cliente: Cliente) void
        +chamarProximo(idGuiche: int, horarioAtual: LocalTime) RegistroAtendimento
        -encontrarGuichePorId(id: int) Guiche
        +getGuiches() Guiche[]
        +getFilaPrioridade() FilaLista~Cliente~
        +getFilaGeral() FilaLista~Cliente~
    }

    class Relatorio {
        +imprimirRelatorio(gerenciador: GerenciadorAtendimento) void
        -imprimirOrdenacoes(registros: RegistroAtendimento[]) void
    }

    %% RELAÇÕES

    %% Implementações de interface
    PilhaLista~T~        ..|>  Pilha~T~
    FilaLista~T~         ..|>  Fila~T~

    %% Herança de ordenação
    OrdenacaoQuickSort~T~ --|>  OrdenacaoAbstract~T~

    %% Comparable
    RegistroPorTempo     ..|>  Comparable
    RegistroPorHorario   ..|>  Comparable

    %% Exceções
    PilhaVaziaException  --|>  RuntimeException
    PilhaCheiaException  --|>  RuntimeException
    FilaVaziaException   --|>  RuntimeException
    FilaCheiaException   --|>  RuntimeException

    %% Composição
    ListaEncadeada~T~    "1"  *--  "0..*"  NoLista~T~
    PilhaLista~T~        "1"  *--  "1"     ListaEncadeada~T~
    FilaLista~T~         "1"  *--  "1"     ListaEncadeada~T~

    %% Modelos
    RegistroAtendimento  "1"  *--  "1"  Cliente
    Guiche               "1"  *--  "1"  TipoAtendimento
    Guiche               "1"  *--  "1"  PilhaLista~RegistroAtendimento~
    Cliente              "1"  *--  "1"  TipoAtendimento

    %% Utils
    RegistroPorTempo     "1"  *--  "1"  RegistroAtendimento
    RegistroPorHorario   "1"  *--  "1"  RegistroAtendimento

    %% Serviços
    GerenciadorAtendimento  "1"  *--  "1..*"  Guiche
    GerenciadorAtendimento  "1"  *--  "2"     FilaLista~Cliente~
    Relatorio                -->              GerenciadorAtendimento
    Relatorio                -->              OrdenacaoQuickSort~T~
    Relatorio                -->              RegistroPorTempo
    Relatorio                -->              RegistroPorHorario
```