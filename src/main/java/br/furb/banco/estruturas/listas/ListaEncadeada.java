package src.main.java.br.furb.banco.estruturas.listas;

public class ListaEncadeada<T> {
  private NoLista<T> primeiro;
  private NoLista<T> ultimo;

  public ListaEncadeada() {
    this.primeiro = null;
  }

  public NoLista<T> getPrimeiro() {
    return this.primeiro;
  }

  public NoLista<T> getUltimo() {
    return ultimo;
  }

  public void inserir(T valor) {
    NoLista<T> novo = new NoLista<>();
    novo.setInfo(valor);
    novo.setProximo(this.primeiro);
    this.primeiro = novo;
  }

  public void inserirNoFinal(T valor) {
    NoLista<T> novo = new NoLista<>();
    novo.setInfo(valor);
    novo.setProximo(null);

    if (this.estaVazia()) {
      this.primeiro = novo;
    } else {
      this.ultimo.setProximo(novo);
    }
    this.ultimo = novo;
  }


  public Boolean estaVazia() {
    return primeiro == null;
  }

  public NoLista<T> buscar(T valor) {
    NoLista<T> ponteiro = this.primeiro;
    while (ponteiro != null) {
      if (ponteiro.getInfo() == valor) {
        return ponteiro;
      }
      ponteiro = ponteiro.getProximo();
    }
    return null;
  }

  public void retirar(T valor) {
    NoLista<T> anterior = new NoLista<>();
    anterior = null;
    NoLista<T> ponteiro = new NoLista<>();
    ponteiro = this.primeiro;
    while (ponteiro != null && ponteiro.getInfo() != valor) {
      anterior = ponteiro;
      ponteiro = ponteiro.getProximo();
    }
    if (ponteiro != null) {
      if (ponteiro == this.primeiro) {
        this.primeiro = ponteiro.getProximo();
      } else {
        anterior.setProximo(ponteiro.getProximo());
      }
    }
  }

  public int obterComprimento() {
    int i = 0;
    NoLista<T> ponteiro = this.primeiro;

    while (ponteiro != null) {
      ponteiro = ponteiro.getProximo();
      i++;
    }
    return i;
  }

  public NoLista<T> obterNo(int idx) {
    NoLista<T> ponteiro = new NoLista<>();
    ponteiro = this.primeiro;
    int i = 0;
    while (ponteiro != null) {
      if (i == idx) {
        break;
      }
      ponteiro = ponteiro.getProximo();
      i++;
    }
    if (ponteiro == null) {
      throw new IndexOutOfBoundsException("Nó não encontrado na lista");
    }
    return ponteiro;
  }

  @Override
  public String toString() {
    String lista = "";
    NoLista<T> ponteiro = this.primeiro;

    while (ponteiro != null) {
      if (ponteiro.getProximo() == null) {
        lista = lista + ponteiro.getInfo();
      } else {
        lista = lista + ponteiro.getInfo() + ", ";
      }
      ponteiro = ponteiro.getProximo();
    }
    return lista;
  }
}
