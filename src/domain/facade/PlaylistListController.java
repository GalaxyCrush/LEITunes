package domain.facade;

import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.playlists.ManualPlaylist;
import domain.playlists.Playlist;
import domain.playlists.PlaylistList;

/**
 * Classe que representa uma controller de PlaylistLists
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class PlaylistListController {

    private PlaylistList playLists;
    private MusicLibrary ml;

    /**
     * Construtor que constroi um PlaylistListController
     */
    public PlaylistListController(PlaylistList playlists, MusicLibrary library) {
        this.ml = library;
        this.playLists = playlists;
    }

    /**
     * Funcao que cria uma playlist com um nome dado
     * 
     * @param name o nome da playlist dado
     */
    public void createPlaylist(String name) {
        this.playLists.add(new ManualPlaylist(name, this.ml));
    }

    /**
     * Funcao que seleciona uma playlist
     * 
     * @param i Indice da playlist a selecionar dado
     */
    public void selectPlaylist(int i) {
        if (0 <= i && i < this.playLists.size()) {
            this.playLists.select(i);
        }
    }

    /**
     * Funcao que indica se alguma playlist esta selecionada
     * 
     * @return True ou false se esta ou nao uma playlist selecionada
     */
    public boolean somePlaylistSelected() {
        return this.playLists.someSelected();
    }

    /**
     * Funcao que devolve a playlist selecionada
     * 
     * @requires {@code somePlaylistSelected()}
     * @return A playlist selecionada
     */
    public Playlist getSelectedPlaylist() {
        return this.playLists.getSelected();
    }

    /**
     * Funcao que remove a playlist selecionada
     */
    public void removePlaylist() {
        this.playLists.remove();
    }

    /**
     * Funcao que retorna um iterador das playlists
     * 
     * @return Um iterador de playlists
     */
    public Iterator<Playlist> iterator() {
        return this.playLists.iterator();
    }

    /**
     * Funcao que devolve o numero de songs na playlist selecionada
     * 
     * @requires {@code somePlaylistSelected()}
     * @return
     */
    public int numberOfSongs() {
        return this.playLists.getSelected().size();
    }

    /**
     * Metodo que vai adicionar a musica selecionada na biblioteca na playlist
     * 
     * @requires {@code somePlaylistSelected()}
     */
    public void addSong() {
        if (this.ml.someSelected()) {
            this.playLists.getSelected().add(this.ml.getSelected());
        }
    }

    /**
     * Funcao que seleciona a i-esima música na playlist selecionada
     * 
     * @requires {@code somePlaylistSelected()}
     * @param i indice da musica dado
     */
    public void selectSong(int i) {
        if (0 <= i && i < getSelectedPlaylist().size()) {
            this.playLists.getSelected().select(i);
        }
    }

    /**
     * Funcao que verifica se uma playlist foi selecionada,
     * e em caso positivo verifica se uma musica na playlist selecionada foi
     * selecionada
     * 
     * @return se a playlist e a musica da mesma foi selecionada
     */
    public boolean someSongSelected() {
        return somePlaylistSelected() && this.getSelectedPlaylist().someSelected();
    }

    /**
     * Funcao que apaga a musica selecionada na playlist selecionada
     * 
     * @requires {@code someSongSelected()}
     */
    public void removeSelectedSong() {
        this.playLists.getSelected().remove();
    }

    /**
     * Funcao que despacha o pedido de selecao da proxima musica para a playlist
     * selecionada
     * 
     * @requires {@code somePlayListSelected()}
     */
    public void nextSong() {
        this.playLists.getSelected().next();
    }

    /**
     * Funcao que despacha o pedido de selecao da musica anterior para a playlist
     * selecionada
     * 
     * @requires {@code somePlayListSelected()}
     */
    public void previousSong() {
        this.playLists.getSelected().previous();
    }

    /**
     * Funcao que poe uma musica para tocar
     */
    public void play() {
        if (someSongSelected()) {
           this.playLists.play(); 
        }
    }

    /**
     * Funcao que para a musica que estava a ser tocada
     */
    public void stop() {
        if (this.playLists.isPlaying()) {
           this.playLists.stop(); 
        }
    }

    /**
     * Representacao textual de um PlaylistListController
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("***** PLAYLISTS *****");
        sb.append(this.playLists.toString());
        return sb.toString();
    }
}
