package lauzhack2020.main;

import java.util.ArrayList;

public class Hospital {

    /**
     * A Hospital has a name, multiple units, who each have a certain number of free beds, used by covid beds, other etc...
     * and a Location.
     */
    String name;
    ArrayList<Unit> units;
    Location location;

    /**
     * Hospital constructor
     * @param name String
     * @param units ArrayList[Unit]
     * @param location location
     */
    public Hospital(String name, ArrayList<Unit> units, Location location) {
        this.name = name;
        this.units = units;
        this.location = location;
    }

    /**
     * Adds a patient to the specified unit
     * @param unit Unit
     * @param covid boolean (is he sick with covid19 ?)
     */
    public void add(Unit unit, boolean covid){
        if (covid) { unit.add();}
        else { unit.addNoCovid();}
    }
}
