package lauzhack2020.main;

import lauzhack2020.main.Enums.Canton;

public class Location {

    /**
     * Basically the address but split into separate fields so that we can after searc hospitals
     * by specific regions, or specific zipcode etc...
     */
    String stName; //Street Name
    int stNb; //Street Number
    int npa;
    String town;
    Canton canton;

    /**
     * Constructor for Location
     * @param stName String
     * @param stNb int
     * @param npa int (Zip code)
     * @param  town String
     * @param canton Canton
     */
    public Location(String stName, int stNb, int npa, String town, Canton canton) {
        this.stName = stName;
        this.stNb = stNb;
        this.npa = npa;
        this.town = town;
        this.canton = canton;
    }

}
