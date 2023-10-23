package domain.playlists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.core.MusicLibrary;
import util.adts.AbsQListWithSelection;

/**
 * Classe que representa uma lista de playlists
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class PlaylistList extends AbsQListWithSelection<Playlist> {

    private MusicLibrary lib;

    /**
     * Construtor de uma PlaylistList
     * 
     * @param library A biblioteca onde se encontram as musicas
     */
    public PlaylistList(MusicLibrary library) {
        super();
        this.lib = library;
        this.add(new MostLikedSongsPlaylist(library));
        this.add(new MostRecentlyAddedSongsPlaylist(library));
    }

    /**
     * Metodo que poe uma musica a tocar
     */
    public void play() {
        super.getSelected().play();
    }

    /**
     * Metodo que verifica se uma musica esta a tocar
     * 
     * @return se uma musica esta a tocar
     */
    public boolean isPlaying() {
        for (Iterator<Playlist> it = super.iterator(); it.hasNext();) {
            if (it.next().isPlaying()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que interrompe uma musica a tocar
     * 
     * @requires {isPlaying()}
     */
    public void stop() {
        for (Iterator<Playlist> it = super.iterator(); it.hasNext();) {
            Playlist current = it.next();
            if (current.isPlaying()) {
                current.stop();
            }
        }
    }

    /**
     * Uma funcao que tem como objetivo retornar uma
     * lista com proposito de usar no construtor da classe.
     *
     * @return A lista
     */
    @Override
    protected List<Playlist> createList() {
        return new ArrayList<>();
    }

    /**
     * Metodo que adiciona uma playlist ah lista, e regista-a para ser um listener
     * 
     * @param e A playlist a ser adicionada a lista dada
     */
    @Override
    public void add(Playlist e) {
        super.add(e);
        this.lib.registerListener(e);
    }

    /**
     * Metodo que remove uma playlist da lista, e remove-a da lista de listeners
     */
    @Override
    public void remove() {
        if (someSelected()) {
            this.lib.unregisterListener(getSelected());
        }
        super.remove();
    }

    /**
     * Representacao textual de uma PlaylistList
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Iterator<Playlist> i = super.iterator(); i.hasNext();) {
            sb.append("\n" + i.next().toString());
        }
        return sb.toString();
    }

}
