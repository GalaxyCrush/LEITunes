package util.adts;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.Test;

/**
 * Classe de testes de uma ArrayQListWithSelectionTests
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class ArrayQListWithSelectionTests {

    /**
     *  Testa o construtor de uma ArrayQListWithSelection
     */
    @Test
    public void testConstructor() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        assertNotNull(lista);
        assertNull(lista.getSelected());
    }
    
    /**
     *  Testa a funcao size de uma ArrayQListWithSelection
     */
    @Test
    public void testSize() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        assertEquals(lista.size(), 0);
    }

    /**
     *  Testa o metodo add de uma ArrayQListWithSelection
     */
    @Test
    public void testAdd() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        assertEquals(lista.get(0), 1);
        assertEquals(lista.get(1), 2);
        assertEquals(lista.size(), 2);
    } 

    /**
     *  Testa a metodo Select e a funcao GetSelected de uma ArrayQListWithSelection
     */
    @Test
    public void testSelectGetSelected() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        lista.select(0);
        assertEquals(lista.getSelected(), 1);
    }
    
    /**
     *  Testa a metodo SomeSelected de uma ArrayQListWithSelection
     */
    @Test
    public void testSomeSelected() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        lista.select(0);
        assertTrue(lista.someSelected());
        lista.remove();
        assertFalse(lista.someSelected());
    }
    
    /**
     *  Testa a funcao GetIndexSelected de uma ArrayQListWithSelection
     */
    @Test
    public void testGetIndexSelected() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        lista.select(0);
        assertEquals(lista.getIndexSelected(), 0);
    }
    
    /**
     *  Testa o metodo Next de uma ArrayQListWithSelection
     */
    @Test
    public void testNext() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        lista.select(0);
        lista.next();
        assertEquals(lista.getSelected(), 2);
        assertEquals(lista.getIndexSelected(), 1);
        lista.next();
        assertNull(lista.getSelected());
    }
    
    /**
     *  Testa o metodo Previous de uma ArrayQListWithSelection
     */
    @Test
    public void testPrevious() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        lista.select(1);
        lista.previous();
        assertEquals(lista.getSelected(), 1);
        assertEquals(lista.getIndexSelected(), 0);
        lista.previous();
        assertNull(lista.getSelected());
    }

    /**
     *  Testa o Iterador de uma ArrayQListWithSelection
     */
    @Test
    public void testIterator() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4); 
        Iterator<Integer> it1 = lista.iterator();
        assertNotNull(it1);
    }
    
    /**
     *  Testa o metodo remove de uma ArrayQListWithSelection
     *  Quando existe algo para remover
     */
    @Test
    public void testRemove() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        lista.select(0);
        assertTrue(lista.someSelected());
        
        lista.remove();
        
        
        assertNull(lista.getSelected());
        assertFalse(lista.someSelected());
         
    }
    
    /**
     *  Testa o metodo remove de uma ArrayQListWithSelection
     *  Quando nao existe algo para remover
     */
    @Test
    public void testRemove2() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.remove();
        assertNull(lista.getSelected());
    }
    
    /**
     *  Testa o toString de uma ArrayQListWithSelection
     */
    @Test
    public void testToString() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        lista.select(0);
        assertEquals(lista.toString(), "\n0 1->\n1 2");
    }
    
    /**
     *  Testa o CreateList de uma ArrayQListWithSelection
     */
    @Test
    public void testCreateList() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        assertNotNull(lista.createList());
    }

    /**
     *  Testa o Equals de uma ArrayQListWithSelection
     *  Quando a lista eh igual pela referencia
     */
    @Test
    public void testEquals1() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        ArrayQListWithSelection<Integer> l = lista;
        assertTrue(l.equals(lista));
    }

    /**
     *  Testa o Equals de uma ArrayQListWithSelection
     *  Quando a lista nao eh igual por ser null
     */
    @Test
    public void testEquals2() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        ArrayQListWithSelection<Integer> l = null;
        assertFalse(lista.equals(l));
    }

    /**
     *  Testa o Equals de uma ArrayQListWithSelection
     *  Quando a lista nao eh igual pelo tipo do objeto
     */
    @Test
    public void testEquals3() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        Object l = "Listinha fixe";
        assertFalse(lista.equals(l));
    }

    /**
     *  Testa o Equals de uma ArrayQListWithSelection
     *  Quando a lista eh igual pelo conteudo
     */
    @Test
    public void testEquals4() {
        ArrayQListWithSelection<Integer> lista = new ArrayQListWithSelection<>();
        lista.add(1);
        lista.add(2);
        ArrayQListWithSelection<Integer> l = new ArrayQListWithSelection<>();
        l.add(1);
        l.add(2);
        assertTrue(lista.equals(l));
    }
}
