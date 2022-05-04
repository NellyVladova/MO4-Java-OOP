package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission{
    private int deadAstronautCounter = 0;
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        List<Astronaut> astronautsList = new ArrayList<>(astronauts);
        List<String> planetItemsList = new ArrayList<>(planet.getItems());
        for (int i = 0; i < astronautsList.size(); i++) {
            Astronaut astronaut = astronautsList.get(i);
            for (int j = 0; j < planetItemsList.size(); j++) {
                String currItem = planetItemsList.get(j);
                astronaut.breath();
                astronaut.getBag().getItems().add(currItem);
                planetItemsList.remove(currItem);
                j--;
                if (!astronaut.canBreath()){
                    this.deadAstronautCounter++;
                    break;
                }
            }
        }
    }

    @Override
    public int getDeadAstronauts() {
        return this.deadAstronautCounter;
    }
}
