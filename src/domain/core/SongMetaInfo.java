package domain.core;

import java.util.List;
import java.util.regex.Pattern;

import util.adts.RegExpMatchable;

/**
 * Um record que representa os metadados de uma Song
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public record SongMetaInfo(String title, String genre, List<String> artists, String album)
        implements RegExpMatchable {

    /**
     * Funcao que vai verificar se existe alguma match de acordo com o regexp dado
     * 
     * @param regexp Pattern de uma song dado
     * @return True ou false se encontrou ou não matches respetivamente.
     */
    @Override
    public boolean matches(String regexp) {
        Pattern pat = Pattern.compile(regexp);
        boolean match = (pat.matcher(title).matches()) || (pat.matcher(genre).matches())
                || (pat.matcher(album).matches());
        for (String currentArtist : artists) {
            match |= (pat.matcher(currentArtist).matches());
        }
        return match;
    }

    /**
     * Representacao textual de uma SongMetaInfo
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + this.title + ", ");
        sb.append(this.album + ", ");
        sb.append(this.genre + ", ");
        sb.append(this.artists.toString() + "]");
        return sb.toString();
    }
}
