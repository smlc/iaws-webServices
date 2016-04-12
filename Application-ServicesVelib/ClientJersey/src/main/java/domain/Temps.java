package domain;

/**
 * Created by mars on 12/04/16.
 */
public class Temps {

    private int heure;
    private int minute;
    private int seconde;

    public Temps(int heure,int minute,int seconde){
        this.heure = heure;
        this.minute = minute;
        this.seconde= seconde;
    }


    public Temps sommeTemps(Temps temps){

        heure += temps.heure;
        minute += temps.minute;
        seconde += temps.seconde;

        if (seconde>60) {
            minute += seconde/60;
            seconde = seconde%60;
        }
        if (minute>60) {
            heure += minute/60;
            minute = minute%60;
        }

        return this;
    }
    public int getSeconde() {
        return seconde;
    }

    @Override
    public String toString() {
        return "Temps{" +
                "heure=" + heure +
                ", minute=" + minute +
                ", seconde=" + seconde +
                '}';
    }

    public int getMinute() {
        return minute;
    }

    public int getHeure() {
        return heure;
    }

}
