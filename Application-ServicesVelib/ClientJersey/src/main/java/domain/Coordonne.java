package domain;

/**
 * Created by mars on 30/03/16.
 */
public class Coordonne {

    double lat,lon;

    /**
     *
     * @param lat latitude
     * @param lon longitude
     */
    public Coordonne(double lat,double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Coordonne{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordonne coordonne = (Coordonne) o;

        if (Double.compare(coordonne.lat, lat) != 0) return false;
        return Double.compare(coordonne.lon, lon) == 0;

    }


}
