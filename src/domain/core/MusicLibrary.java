package domain.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;
import util.adts.AbsQListWithSelection;
import util.observer.Listener;
import util.observer.Subject;

/**
 * Classe que representa uma biblioteca de musicas
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public class MusicLibrary extends
        AbsQListWithSelection<Song>
        implements Subject<SongLibraryEvent>, PropertyChangeListener {

    private boolean nowPlaying = false;
    private ArrayList<Listener<SongLibraryEvent>> listeners;
    private Player p = PlayerFactory.INSTANCE.getPlayer();
    private ISong currentSong = null;

    /**
     * Construtor da classe que constroi uma bibioteca de musicas
     */
    public MusicLibrary() {
        super();
        this.listeners = new ArrayList<>();
        this.p.addListener(this);
    }

    /**
     * Metodo que vai por a tocar a musica selecionada e determinar
     * a interrupção da musica que estiver a tocar na altura
     * 
     * @requires {@code someSelected()}
     */
    public void play() {
        if (isPlaying()) {
            stop();
        }
        this.p.load(super.getSelected().getFilename());
        this.currentSong = this.getSelected();
        this.p.play();
        this.nowPlaying = true;
    }

    /**
     * Funcao que verifica se ha musica a tocar
     * 
     * @return True ou false se uma musica está ou não a tocar respetivamente
     */
    public boolean isPlaying() {
        return this.nowPlaying;
    }

    /**
     * Metodo que vai parar a musica que esta a tocar
     * 
     * @requires {@code isPlaying()}
     */
    public void stop() {
        this.p.stop();
        this.nowPlaying = false;
        this.currentSong = null;
    }

    /**
     * Incrementa o valor do rate a um imediatamente acima,
     * ou entao fica com o mesmo se for o maximo
     */
    public void incRateSelected() {
        if (someSelected() && !super.getSelected().getRating().equals(Rate.GOD)) {
            super.getSelected().incRating();
            emitEvent(new SongRatedLibraryEvent(super.getSelected(), this));
        }
    }

    /**
     * Decrementa o valor do rate a um imediatamente abaixo,
     * ou entao fica com o mesmo se for o minimo
     */
    public void decRateSelected() {
        if (someSelected() && !super.getSelected().getRating().equals(Rate.NO_RATE)) {
            super.getSelected().decRating();
            emitEvent(new SongRatedLibraryEvent(super.getSelected(), this));
        }
    }

    /**
     * Funcao que retorna uma estutura iteravel
     * com as musicas que dao match ao regex dado
     * 
     * @param reexp Uma expressao para encontrar matches
     * @return Uma estutura iteravel com as matches
     */
    public Iterable<ISong> getMatches(String reexp) {
        ArrayList<ISong> matches = new ArrayList<>();
        if (reexp != null) {
            for (ISong song : getSongs()) {
                if (song.matches(reexp)) {
                    matches.add(song);
                }
            }
        }
        return matches;
    }

    /**
     * Funcao que devolve uma estrutura iteravel com as musicas da
     * biblioteca em ordem propria
     * 
     * @return Um iteravel com as musicas da biblioteca
     */
    public Iterable<ISong> getSongs() {
        List<ISong> listSongs = new ArrayList<>();
        for (ISong actual : this) {
            listSongs.add(actual);
        }
        return listSongs;
    }

    /**
     * Emits a given event to the listeners
     * 
     * @param e event that occurred
     */
    @Override
    public void emitEvent(SongLibraryEvent e) {
        for (Listener<SongLibraryEvent> l : this.listeners) {
            l.processEvent(e);
        }
    }

    /**
     * Registers a new listener
     * 
     * @param obs listener to be added
     */
    @Override
    public void registerListener(Listener<SongLibraryEvent> obs) {
        listeners.add(obs);
    }

    /**
     * Removes the registry of the given listener
     * 
     * @param obs listener to be removed
     */
    @Override
    public void unregisterListener(Listener<SongLibraryEvent> obs) {
        listeners.remove(obs);

    }

    /**
     * Adiciona o elemento que passa a ser o elemento selecionado
     * 
     * @param e O elemento selecionado
     */
    @Override
    public void add(Song e) {
        super.add(e);
        emitEvent(new SongAddedLibraryEvent(e, this));
    }

    /**
     * Apaga o elemento selecionado e emite o evento se {@code someSelected()},
     * e não faz nada no caso contrário
     */
    @Override
    public void remove() {
        if (super.someSelected()) {
            Song eventSong = super.getSelected();
            super.remove();
            emitEvent(new SongRemovedLibraryEvent(eventSong, this));
        }
    }

    /**
     * Metodo que vai verificar se uma propriedade mudou,
     * verifica o tipo e o que mudou e faz a mudança relacionada a esse evento
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue().equals(Player.PlayingState.ENDED) && isPlaying()) {
            this.currentSong.incTimesPlayed();
            if (someSelected()) {
                next();
            }
            if (someSelected()) {
                play();
            } else {
                this.currentSong = null;
            }
        } else if (evt.getNewValue().equals(Player.PlayingState.STOPED) && isPlaying()) {
            this.nowPlaying = false;
            this.currentSong = null;
        }
    }

    /**
     * Uma funcao que tem como objetivo retornar uma
     * lista com proposito de usar no construtor da classe.
     *
     * @return A lista
     */
    @Override
    protected List<Song> createList() {
        return new ArrayList<>();
    }
    
    /**
     * Metodo que verifica se duas MusicLibrary sao iguais
     * 
     * @return se duas MusicLibrary sao iguais
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
        return super.equals(other);
    }

    /**
     * Representacao textual de uma MusicLibrary
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
