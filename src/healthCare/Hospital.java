package healthCare;

import gps.Location;

import java.util.ArrayList;

public class Hospital {

    /**
     * A Hospital has a name, multiple units, who each have a certain number of free beds, used by covid beds, other etc...
     * and a Location.
     */
    String name;
    ArrayList<Unit> units;
    Location location;
    int FreeBedsTot;
    int capacityTot; //how many beds it has in total

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
        computeCapacity();
        computeFreeBedsTot();
    }

    /**
     * Adds a patient to the specified unit, and handles the change in nb of beds used/free
     * @param unit Unit
     * @param covid boolean (is he sick with covid19 ?)
     */
    public void add(Unit unit, boolean covid) {
        if (covid) { unit.add(); }
        else { unit.addNoCovid(); }
    }

    /**
     * Remove a patient to the specified unit, and handles the change in nb of beds used/free
     * @param unit Unit
     * @param covid boolean (is he sick with covid19 ?)
     */
    public void remove(Unit unit, boolean covid) {
        if (covid) { unit.remove(); }
        else { unit.removeNoCovid(); }
    }

    /**
     * compute the total number of bed (used or not) in all units
     * and assigns it to the field "capacityTot"
     */
    private void computeCapacity() {
        for(Unit unit : units) {
            capacityTot += unit.getCapacity();
        }
    }

    /**
     * Compute the total nb of free beds in all units
     * and assigns it to the field "FreeBedsTot"
     */
    private void computeFreeBedsTot() {
        for (Unit unit : units){
            FreeBedsTot += unit.getNbFreeBeds();
        }
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
