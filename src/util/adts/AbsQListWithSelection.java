package util.adts;

import java.util.List;
import java.util.Iterator;

/**
 * Classe que representa uma AbsQListWithSelection onde eh representada por uma lista e um elemento selecionado
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public abstract class AbsQListWithSelection<E> implements QListWithSelection<E> {

    private List<E> lista;
    private E selected;

    /**
     * Construtor de uma QListWithSelection abstrata
     * 
     */
    protected AbsQListWithSelection() {
        this.lista = createList();
        this.selected = null;
    }

    /**
     * Uma funcao que tem como objetivo retornar uma
     * lista com proposito de usar no construtor para
     * inicializar a QListWithSelection.
     *
     * @return A lista
     */
    protected abstract List<E> createList();

    /**
     * Returns the number of elements in the list
     * 
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return this.lista.size();
    }

    /**
     * Assumindo que {@code 0<=i<size()}, seleciona o elemento na posicao i
     * 
     * @param i A posicao
     */
    @Override
    public void select(int i) {
        this.selected = this.lista.get(i);
    }

    /**
     * Indica se há um elemento selecionado
     * 
     * @return True ou false se ha ou nao um elemento selecionado respetivamente
     */
    @Override
    public boolean someSelected() {
        return this.selected != null;
    }

    /**
     * Assumindo que {@code someSelected()}, devolve a sua posição na lista.
     * 
     * @return A posicao na lista.
     */
    @Override
    public int getIndexSelected() {
        return this.lista.indexOf(this.selected);
    }

    /**
     * Seleciona o elemento na posição seguinte à selecionada, se
     * {@code getIndexSelected() < size() -1} senão, deixa de haver um elemento
     * selecionado
     */
    @Override
    public void next() {
        if (getIndexSelected() < size() - 1) {
            this.selected = this.lista.get(getIndexSelected() + 1);
        } else {
            this.selected = null;
        }
    }

    /**
     * Seleciona o elemento na posição anterior à selecionada, se
     * {@code getIndexSelected()>0 } senão, deixa de haver um elemento selecionado.
     */
    @Override
    public void previous() {
        if (getIndexSelected() > 0) {
            this.selected = this.lista.get(getIndexSelected() - 1);
        } else {
            this.selected = null;
        }
    }

    /**
     * Returns the element at position i
     * 
     * @param i the position of the element to return
     * @requires 0 <= i < size()
     * @return the element at position i
     */
    @Override
    public E get(int i) {
        return this.lista.get(i);
    }

    /**
     * Returns an iterator over the elements in the list
     * 
     * @return an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<E> iterator() {
        return this.lista.iterator();
    }

    /**
     * Adiciona o elemento que passa a ser o elemento selecionado
     * 
     * @param e O elemento selecionado
     */
    @Override
    public void add(E e) {
        this.selected = e;
        this.lista.add(e);
    }

    /**
     * Apaga o elemento selecionado,se {@code someSelected()}
     * e não faz nada no caso contrário
     */
    @Override
    public void remove() {
        if (someSelected()) {
            this.lista.remove(getIndexSelected());
            this.selected = null;
        }
    }

    /**
     * assumindo que há um elemento selecionado, o devolve
     * 
     * @return O elemento selecionado
     */
    @Override
    public E getSelected() {
        return this.selected;
    }

    /**
     * Metodo que verifica se um objetos eh igual a uma AbsQListWithSelection
     * 
     * @param other O objeto a ser comparado
     * @return True ou false se o objeto eh ou nao igual
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }

        AbsQListWithSelection<?> listE = (AbsQListWithSelection<?>) other;

        if (listE.size() != this.size()) {
            return false;
        }
        int i = 0;
        for (Iterator<?> it = listE.iterator(); it.hasNext() && i < lista.size();) {
            if (!it.next().equals(this.lista.get(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * Representacao textual de uma QListWithSelection
     * 
     * @return A lista em forma textual
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append("\n" + i + " " + this.get(i).toString() + (getIndexSelected() == i ? "->" : ""));
        }
        return sb.toString();
    }
    
    
}
