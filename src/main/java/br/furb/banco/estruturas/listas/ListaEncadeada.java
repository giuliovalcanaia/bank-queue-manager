public class ListaEncadeada<T> {
  private NoLista<T> primeiro;

  ListaEncadeada() {
    this.primeiro = null;
  }

  public NoLista<T> getPrimeiro() {
    return this.primeiro;
  }

  public void inserir(T valor) {
    NoLista<T> novo = new NoLista<>();
    novo.info = valor;
    novo.setProximo(this.primeiro);
    this.primeiro = novo;
  }

  public Boolean estaVazia() {
    if (primeiro == null) {
      return true;
    } else {
      return false;
    }
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
    while (ponteiro != null && ponteiro.info != valor) {
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
  // Falta implementar a formatação para o último objeto
  public String toString() {
    String lista = new String();
    NoLista<T> ponteiro = new NoLista<>();
    ponteiro = this.primeiro;

    while (ponteiro != null) {
      if (ponteiro == this.primeiro) {
        lista = (String) ponteiro.info + ", ";
      }
      if (ponteiro.getProximo() == null) {
        lista = lista + ponteiro.getInfo();
        return lista;
      }
      lista = lista + ponteiro.getInfo() + ", ";
      ponteiro = ponteiro.getProximo();
    }
    return lista;
  }
}
