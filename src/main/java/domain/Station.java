package domain;

/**
 * Created by mars on 20/03/16.
 */
public class Station {

    private int stationNumber;
    private String contractName;
    private String stationName;
    private String adresse;
    private boolean isOpen;
    private int nombrePlaceDisponible;
    private int nombreVeloDisponible;

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getNombrePlaceDisponible() {
        return nombrePlaceDisponible;
    }

    public void setNombrePlaceDisponible(int nombrePlaceDisponible) {
        this.nombrePlaceDisponible = nombrePlaceDisponible;
    }

    public int getNombreVeloDisponible() {
        return nombreVeloDisponible;
    }

    public void setNombreVeloDisponible(int nombreVeloDisponible) {
        this.nombreVeloDisponible = nombreVeloDisponible;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationNumber=" + stationNumber +
                ", contractName='" + contractName + '\'' +
                ", stationName='" + stationName + '\'' +
                ", adresse='" + adresse + '\'' +
                ", isOpen=" + isOpen +
                ", nombrePlaceDisponible=" + nombrePlaceDisponible +
                ", nombreVeloDisponible=" + nombreVeloDisponible +
                '}';
    }
}
