package main;

import main.Enums.UnitType;

public class Unit {
//Different Areas/Unit like intensive care unit, normal care unit etc...

    /**
     * A unit has a type (cardiology, emergency etc...) and multiple infos about the capacity of patient it can take/handle
     */
    final UnitType type;
    int nbBedTot;
    int nbFreeBeds;
    int nbUsedBeds;
    int nbCovidBeds; // number of beds used by patient ill of covid19
    int nbOtherBeds; // number of beds used by patient ill from something else than covid19

    /**
     * Unit constructor
     * @param type UnitType
     * @param nbBedTot int
     * @param nbCovidBeds int
     * @param nbOtherBeds int
     */
    public Unit(UnitType type, int nbBedTot, int nbCovidBeds, int nbOtherBeds) {
        this.type = type;
        this.nbBedTot = nbBedTot;
        this.nbCovidBeds = nbCovidBeds;
        this.nbOtherBeds = nbOtherBeds;
        this.nbUsedBeds = nbCovidBeds + nbOtherBeds;
        this.nbFreeBeds = nbBedTot - nbUsedBeds;

    }

    /**
     * Add an ill-covi19 patient to the unit
     * handle the addition & substraction of beds no need to redo it
     */
    public void add() {
        if (nbFreeBeds != 0 || nbUsedBeds == nbBedTot) {
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
        if (nbFreeBeds != 0 || nbUsedBeds == nbBedTot) {
            this.nbUsedBeds += 1;
            this.nbOtherBeds += 1;
            this.nbFreeBeds -= 1;
        }
        else { System.out.println("Sorry, this unit is full. Try somewhere else."); }

    }
}
