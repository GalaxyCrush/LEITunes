package util.adts;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import domain.core.Rate;
import domain.core.Song;
import domain.core.SongMetaInfo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Classe de testes de uma Song
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class SongTests {

    private Song testSong;

    /**
     * Cria uma musica padrao antes de todo teste
     */
    @BeforeEach
    public void init() {
        SongMetaInfo info = new SongMetaInfo("Teste1", "Pop", Arrays.asList("TestArtist"), "unknown");
        this.testSong = new Song(info, "filename.mp3");
    }

    /**
     *  Testa o construtor de uma Song
     */
    @Test
    public void testConstructor() {
        assertNotNull(this.testSong);
    }

    /**
     *  Testa a funcao IncTimesPlayed de uma Song
     */
    @Test
    public void testIncTimesPlayed() {
        testSong.incTimesPlayed();
        int depois = this.testSong.getTimesPlayed();
        assertEquals(depois, 1);
    }

    /**
     *  Testa a funcao GetRating de uma Song
     */
    @Test
    public void testGetRating() {
        Rate rate = this.testSong.getRating();
        assertEquals(rate, Rate.NO_RATE);
    }
    
   /**
     *  Testa o metodo IncRating de uma Song
     *  Quando a musica nao tem o rate maximo
     */
    @Test
    public void testIncRating() {
        this.testSong.incRating();
        int actual = Rate.getRateValue(this.testSong.getRating());
        assertEquals(actual, 1);
        
    }

    /**
     *  Testa o metodo IncRating de uma Song
     *  Quando a musica tem o rate maximo
     */
    @Test
    public void testIncRating2() {
        this.testSong.incRating();
        this.testSong.incRating();
        this.testSong.incRating();
        this.testSong.incRating();
        this.testSong.incRating();
        this.testSong.incRating();
        int apos = Rate.getRateValue(testSong.getRating());
        assertEquals(apos, 5);
    }
    
    /**
     *  Testa o metodo DecRating de uma Song
     *  Quando a musica tem o rate minimo
     */
    @Test
    public void testDecRating() {
        int actual = Rate.getRateValue(testSong.getRating());
        this.testSong.decRating();
        int apos = Rate.getRateValue(testSong.getRating());
        assertEquals(actual,apos);  
    }
    
    /**
     *  Testa o metodo DecRating de uma Song
     *  Quando a musica nao tem o rate minimo
     */
    @Test
    public void testDecRating2() {
    	this.testSong.incRating();
    	this.testSong.incRating();
        this.testSong.decRating();
        int apos = Rate.getRateValue(testSong.getRating());
        assertEquals(apos,1);
    }

    /**
     *  Testa a funcao GetSongTitle de uma Song
     */
    @Test
    public void testGetSongTitle() {
        String name = this.testSong.getSongTitle();
        assertEquals(name, "Teste1");
    }

    /**
     *  Testa a funcao GetGenre de uma Song
     */
    @Test
    public void testGetGenre() {
        String genre = this.testSong.getGenre();
        assertEquals(genre, "Pop");
    }

    /**
     *  Testa a funcao GetArtist de uma Song
     */
    @Test
    public void testGetArtist() {
        List<String> aux = testSong.getArtists();
        Collections.sort(aux);
        List<String> compare = Arrays.asList("TestArtist");
        Collections.sort(compare);
        assertEquals(aux, compare);
    }

    /**
     *  Testa a funcao GetAlbum de uma Song
     */
    @Test
    public void testGetAlbum() {
        String album = this.testSong.getAlbum();
        assertEquals(album, "unknown");
    }

    /**
     *  Testa a funcao GetFileName de uma Song
     */
    @Test
    public void testGetFileName() {
        String filename = this.testSong.getFilename();
        assertEquals(filename, "filename.mp3");
    }

    /**
     *  Testa a funcao Matches de uma Song
     *  Utilizando o titulo da musica
     */
    @Test
    public void testGetMatchesTitle() {
        assertTrue(this.testSong.matches("Teste1"));
    }

    /**
     *  Testa a funcao Matches de uma Song
     *  Utilizando o genero da musica
     */
    @Test
    public void testGetMatchesGenre() {
        assertTrue(this.testSong.matches("Pop"));
    }

    /**
     *  Testa a funcao Matches de uma Song
     *  Utilizando o album da musica
     */
    @Test
    public void testGetMatchesAlbum() {
        assertTrue(this.testSong.matches("unknown"));
    }

    /**
     *  Testa a funcao Matches de uma Song
     *  Utilizando os artistas da musica
     */
    @Test
    public void testGetMatchesArtists() {
        assertTrue(this.testSong.matches("TestArtist"));
    }

    /**
     *  Testa o toString de uma Song
     */
    @Test
    public void testToString() {
        String song = "[Teste1, unknown, Pop, [TestArtist]] --- 0 -- 0";
        assertEquals(song, this.testSong.toString());
    }

    /**
     * Testa o equals no caso de uma song ser passada por referencia
     */
    @Test
    public void testEquals1() {
        Song s = this.testSong;
        assertTrue(s.equals(this.testSong));
    }

    /**
     * Testa o equals no caso de uma song ser null
     */
    @Test
    public void testEquals2() {
        Song s = null;
        assertFalse(this.testSong.equals(s));
    }

    /**
     * Testa o equals no caso de uma song ser igualado a outro tipo de objeto
     */
    @Test
    public void testEquals3() {
        Object s = "musiquinha fixe";
        assertFalse(this.testSong.equals(s));
    }

    /**
     * Testa o equals no caso de uma song ter o filename diferente
     */
    @Test
    public void testEquals4() {
        SongMetaInfo sInfo = new SongMetaInfo("Teste1", "Pop", Arrays.asList("TestArtist"), "unknown");
        Song s = new Song(sInfo, "WRONG");
        assertFalse(s.equals(this.testSong));
    }

    /**
     * Testa o equals no caso de uma song ter o nome diferente
     */
    @Test
    public void testEquals5() {
        SongMetaInfo sInfo = new SongMetaInfo("WRONG", "Pop", Arrays.asList("TestArtist"), "unknown");
        Song s = new Song(sInfo, "filename.mp3");
        assertFalse(s.equals(this.testSong));
    }

    /**
     * Testa o equals no caso de uma song ter o genero diferente
     */
    @Test
    public void testEquals6() {
        SongMetaInfo sInfo = new SongMetaInfo("Teste1", "WRONG", Arrays.asList("TestArtist"), "unknown");
        Song s = new Song(sInfo, "filename.mp3");
        assertFalse(s.equals(this.testSong));
    }
    
    /**
     * Testa o equals no caso de uma song ter os artistas diferentes
     */
    @Test
    public void testEquals7() {
        SongMetaInfo sInfo = new SongMetaInfo("Teste1", "TestArtist", Arrays.asList("WRONG"), "unknown");
        Song s = new Song(sInfo, "filename.mp3");
        assertFalse(s.equals(this.testSong));
    }

    /**
     * Testa o equals no caso de uma song ter o album diferente
     */
    @Test
    public void testEquals8() {
        SongMetaInfo sInfo = new SongMetaInfo("Teste1", "Pop", Arrays.asList("TestArtist"), "WRONG");
        Song s = new Song(sInfo, "filename.mp3");
        assertFalse(s.equals(this.testSong));
    }

    /**
     * Testa o equals no caso de uma song ter o numero de vezes tocadas diferente
     */
    @Test
    public void testEquals9() {
        SongMetaInfo sInfo = new SongMetaInfo("Teste1", "Pop", Arrays.asList("TestArtist"), "unknown");
        Song s = new Song(sInfo, "filename.mp3");
        this.testSong.incTimesPlayed();
        assertFalse(s.equals(this.testSong));
    }

    /**
     * Testa o equals no caso de uma song ter rating diferentes
     */
    @Test
    public void testEquals10() {
        SongMetaInfo sInfo = new SongMetaInfo("Teste1", "Pop", Arrays.asList("TestArtist"), "unknown");
        Song s = new Song(sInfo, "filename.mp3");
        this.testSong.incRating();
        assertFalse(s.equals(this.testSong));
    }    
}
