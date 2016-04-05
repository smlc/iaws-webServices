package services;

import domain.Station;

import java.util.List;

/**
 * Created by mars on 04/04/16.
 */
public interface MediateurService {

    public List<Station> getStationsNonVides (String contract);

    public List<Station> getStationsNonCompletes (String contract);
}
