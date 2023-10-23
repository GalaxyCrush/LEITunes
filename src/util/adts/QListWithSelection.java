package util.adts;

/**
 * Representa listas QList<E> que podem ter zero ou elemento selecionado e
 * permitem a remoção
 * deste elemento.
 */
public interface QListWithSelection<E> extends QList<E> {

    /**
     * Assumindo que {@code 0<=i<size()}, seleciona o elemento na posicao i
     * 
     * @param i A posicao
     */
    void select(int i);

    /**
     * Adiciona o elemento que passa a ser o elemento selecionado
     * 
     * @param e O elemento selecionado
     */
    @Override
    void add(E e);

    /**
     * Indica se há um elemento selecionado
     * 
     * @return True ou false se ha ou nao um elemento selecionado respetivamente
     */
    boolean someSelected();

    /**
     * Assumindo que {@code someSelected()}, devolve a sua posição na lista.
     * 
     * @return A posicao na lista.
     */
    int getIndexSelected();

    /**
     * Seleciona o elemento na posição seguinte à selecionada, se
     * {@code getIndexSelected() < size() -1} senão, deixa de haver um elemento
     * selecionado
     */
    void next();

    /**
     * Seleciona o elemento na posição anterior à selecionada, se
     * {@code getIndexSelected()>0 } senão, deixa de haver um elemento selecionado.
     */
    void previous();

    /**
     * Apaga o elemento selecionado,se {@code someSelected()}
     * e não faz nada no caso contrário
     */
    void remove();

    /**
     * assumindo que há um elemento selecionado, o devolve
     * 
     * @return O elemento selecionado
     */
    E getSelected();

}
