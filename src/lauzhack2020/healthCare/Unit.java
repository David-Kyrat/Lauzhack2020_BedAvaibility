package lauzhack2020.healthCare;

import lauzhack2020.healthCare.administration.UnitType;

public class Unit {
//Different Areas/Unit like intensive care unit, normal care unit etc...

    /**
     * A unit has a type (cardiology, emergency etc...) and multiple infos about the capacityTot of patient it can take/handle
     */
    private final UnitType type;
    private final int capacity;
    private int nbFreeBeds;
    private int nbUsedBeds;
    private int nbCovidBeds; // number of beds used by patient ill of covid19
    private int nbOtherBeds; // number of beds used by patient ill from something else than covid19

    /**
     * Unit constructor
     * @param type UnitType
     * @param capacity int
     * @param nbCovidBeds int
     * @param nbOtherBeds int
     */
    public Unit(UnitType type, int capacity, int nbCovidBeds, int nbOtherBeds) {
        this.type = type;
        this.capacity = capacity;
        this.nbCovidBeds = nbCovidBeds;
        this.nbOtherBeds = nbOtherBeds;
        this.nbUsedBeds = nbCovidBeds + nbOtherBeds;
        this.nbFreeBeds = capacity - nbUsedBeds;

    }

    /**
     * Add an ill-covi19 patient to the unit
     * handle the addition & substraction of beds no need to redo it
     */
    public void add() {
        if (nbFreeBeds != 0 || nbUsedBeds == capacity) {
            this.nbUsedBeds += 1;
            this.nbCovidBeds += 1;
            this.nbFreeBeds -= 1;
        }
        else { System.out.println("Sorry, this unit is full. Try somewhere else."); }
    }

    /**
     * Add an ill patient (not Covid) to the unit
     * handle the addition & substraction of beds no need to redo it
     */
    public void addNoCovid() {
        if (nbFreeBeds != 0 || nbUsedBeds == capacity) {
            this.nbUsedBeds += 1;
            this.nbOtherBeds += 1;
            this.nbFreeBeds -= 1;
        }
        else { System.out.println("Sorry, this unit is full. Try somewhere else."); }

    }

    /**
     * Remove an ill-covid19 patient to the unit
     *handle the addition & substraction of beds no need to redo it
     */
    public void remove() {
        if(nbCovidBeds != 0) {
           nbCovidBeds -= -1;
           nbUsedBeds -= -1;
           nbFreeBeds += 1;
        }
    }

    /**
     * remove an ill patient (not Covid) to the unit
     * handle the addition & substraction of beds no need to redo it
     */
    public void removeNoCovid() {
        if(nbOtherBeds!= 0) {
            nbOtherBeds -= 1;
            nbUsedBeds -= 1;
            nbFreeBeds += 1;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNbUsedBeds() {
        return nbUsedBeds;
    }

    public int getNbFreeBeds() {
        return nbFreeBeds;
    }

    public int getNbCovidBeds() {
        return nbCovidBeds;
    }

    public int getNbOtherBeds() {
        return nbOtherBeds;
    }

    @Override
    public String toString() {
        return "____\n type + : \n" +
               "Number of Free beds : " + nbFreeBeds + "\n"
                + "Number of Used beds : " + nbUsedBeds + "\n"
                ;
    }
}
