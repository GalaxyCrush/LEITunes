package util.adts;

import java.util.ArrayList;
import java.util.List;

/**
 * classe que fornece uma implementacao de QListWithSelection baseada em arrays
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public final class ArrayQListWithSelection<E> extends AbsQListWithSelection<E> {

    /**
     * Uma funcao que tem como objetivo retornar uma
     * lista com proposito de usar no construtor para
     * inicializar a QListWithSelection.
     *
     * @return A lista
     */
    @Override
    protected List<E> createList() {
        return new ArrayList<>();
    }
}
