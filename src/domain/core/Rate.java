package domain.core;

/**
 * Enum que representa os posiveis ratings das musicas
 * 
 * @author Joao Pereira fc58189
 * @author Daniel Nunes fc58257
 */
public enum Rate {
    NO_RATE, BAD, MID, OK, GOOD, GOD;

    /**
     * Incrementa o valor do rating em 1
     *
     * @param value o rating a incrementar
     * @return O rating novo
     */
    public static int incRateByValue(int value) {
        return value == 5 ? value : value + 1;
    }

    /**
     * Decrementa o valor do rating em 1
     *
     * @param value o rating a incrementar
     * @return O rating novo
     */
    public static int decRateByValue(int value) {
        return value == 0 ? value : value - 1;
    }

    /**
     * Incrementa o valor do rating em 1 a partir de um rating
     * 
     * @param r o rating a incrementar
     * @return O rating novo
     */
    public static Rate incRateByRate(Rate r) {
        switch (r) {
            case NO_RATE:
                return BAD;
            case BAD:
                return MID;
            case MID:
                return OK;
            case OK:
                return GOOD;
            case GOOD:
                return GOD;
            case GOD:
                return GOD;
            default:
                return NO_RATE;
        }
    }

    /**
     * Decrementa o valor do rating em 1 a partir de um rating
     * 
     * @param r o rating a incrementar
     * @return O rating novo
     */
    public static Rate decRateByRate(Rate r) {
        switch (r) {
            case NO_RATE:
                return NO_RATE;
            case BAD:
                return BAD;
            case MID:
                return BAD;
            case OK:
                return MID;
            case GOOD:
                return OK;
            case GOD:
                return GOOD;
            default:
                return NO_RATE;
        }
    }

    /**
     * Devolve o valor dado um inteiro em rating
     * 
     * @param i O rating em inteiro
     * @return O rating correspondente
     */
    public static Rate getRateByValue(int i) {
        switch (i) {
            case 0:
                return NO_RATE;
            case 1:
                return BAD;
            case 2:
                return MID;
            case 3:
                return OK;
            case 4:
                return GOOD;
            case 5:
                return GOD;
            default:
                return NO_RATE;
        }
    }

    /**
     * Devolve o valor dado um ranting em inteiro
     * 
     * @param r O rating
     * @return O inteiro correspondente
     */
    public static int getRateValue(Rate r) {
        switch (r) {
            case NO_RATE:
                return 0;
            case BAD:
                return 1;
            case MID:
                return 2;
            case OK:
                return 3;
            case GOOD:
                return 4;
            case GOD:
                return 5;
            default:
                return 0;
        }
    }

    /**
     * Metodo que retorna uma demostracao textual de um Rate
     * 
     * @return Rate
     */
    @Override
    public String toString() {
        switch (this) {
            case NO_RATE:
                return "Unrated";
            case BAD:
                return "Bad";
            case MID:
                return "Mid";
            case GOOD:
                return "Good";
            case GOD:
                return "God";
            default:
                return "";
        }
    }

}
