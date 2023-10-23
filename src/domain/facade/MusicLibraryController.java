package domain.facade;

import java.util.Optional;

import domain.core.MusicLibrary;
import servicos.SongMaker;

/**
 * Classe que representa um controlador de MusicLibrary
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class MusicLibraryController {

    protected MusicLibrary lib;

    /**
     * Construtor que constroi uma MusicLibraryController
     * 
     * @param library A biblioteca a controlar
     */
    public MusicLibraryController(MusicLibrary library) {
        this.lib = library;
    }

    /**
     * Funcao que retorna o numero de musicas na biblioteca
     * 
     * @return O numero de musicas na biblioteca
     */
    public int numberOfSongs() {
        return this.lib.size();
    }

    /**
     * Metodo que adiciona uma musica ah biblioteca
     * 
     * @param filename O nome do ficheiro da musica
     * @requires filename eh um ficheiro mp3
     */
    public void addSong(String filename) {
        SongMaker make = new SongMaker();
        this.lib.add(make.createSong(filename));
    }

    /**
     * Funcao que seleciona uma musica na biblioteca
     * 
     * @param i indice da musica a ser selecionada
     */
    public void selectSong(int i) {
        if (0 <= i && i < numberOfSongs()) {
            this.lib.select(i);
        }
    }

    /**
     * Funcao que retorna a selected song se existir
     * e nada no caso contrario
     * 
     * @return A selected song ou empty
     */
    public Optional<ISong> getSelectedSong() {
        if (this.lib.someSelected()) {
            return Optional.of(this.lib.getSelected());
        }
        return Optional.empty();
    }

    /**
     * Metodo que remove a musica selecionada
     */
    public void removeSelectedSong() {
        this.lib.remove();
    }

    /**
     * Metodo que vai por a tocar a musica selecionada
     */
    public void play() {
        if (this.lib.someSelected()) {
          this.lib.play();
        }
    }

    /**
     * Metodo que vai para a reproducao da musica selecionada
     */
    public void stop() {
        if (this.lib.isPlaying()) {
            this.lib.stop();   
        }
    }

    /**
     * Metodo que incrementa o rate de uma musica, se for o maximo mantem igual
     */
    public void incRateSelected() {
        this.lib.incRateSelected();
    }

    /**
     * Metodo que decrementa o rate de uma musica, se for o minimo mantem igual
     */
    public void decRateSelected() {
        this.lib.decRateSelected();
    }

    /**
     * Funcao que retorna um tipo iteravel com as matches com o reexp
     * 
     * @param reexp O regex que temos que comparar
     * @return Um tipo iteravel com as matches
     */
    public Iterable<ISong> getMatches(String reexp) {
        return this.lib.getMatches(reexp);
    }

    /**
     * Funcao que retorna um tipo iteravel
     * com as musicas da biblioteca
     * 
     * @return Um tipo iteravel com songs
     */
    public Iterable<ISong> getSongs() {
        return this.lib.getSongs();
    }

    /**
     * Representacao textual de uma MusicLibraryController
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("*****MUSIC LIBRARY****");
        sb.append(this.lib.toString());
        return sb.toString();
    }

}
